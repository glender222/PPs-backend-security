package com.example.demo.login.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.AuthCreateUserRequest;
import com.example.demo.Dto.AuthLoginRequest;
import com.example.demo.Dto.AuthResponse;
import com.example.demo.Dto.DirectoraCreateRequest;
import com.example.demo.Dto.PracticanteCreateRequest;
import com.example.demo.entity.Directora;
import com.example.demo.entity.EscuelaProfesional;
import com.example.demo.entity.Linea;
import com.example.demo.entity.Persona;
import com.example.demo.login.Entity.RoleEntity;
import com.example.demo.login.Entity.RoleEnum;
import com.example.demo.login.Entity.UserEntity;
import com.example.demo.login.Repository.RoleRepository;
import com.example.demo.login.Repository.UserRepository;
import com.example.demo.login.config.JwtUtils;
import com.example.demo.repository.DirectoraRepository;
import com.example.demo.repository.EscuelaProfesionalRepository;
import com.example.demo.repository.LineaRepository;
import com.example.demo.repository.PersonaRepository;
import com.example.demo.service.CoordinadorService;
import com.example.demo.service.EmailService;
import com.example.demo.service.PracticanteService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

 private final PersonaRepository personaRepository;
    private final EscuelaProfesionalRepository escuelaProfesionalRepository;
    private final CoordinadorService coordinadorService;
    private final EmailService emailService;
    private final DirectoraRepository directoraRepository;
    private final LineaRepository lineaRepository;
    private final PracticanteService practicanteService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findUserEntityByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no existe."));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        userEntity.getRoles()
                .forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));

       /*   userEntity.getRoles().stream()
                .flatMap(role -> role.getPermissionList().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));*/


                
        return new User(userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.isEnabled(),
                userEntity.isAccountNoExpired(),
                userEntity.isCredentialNoExpired(),
                userEntity.isAccountNoLocked(),
                authorityList);
    }

    public AuthResponse loginUser(AuthLoginRequest authLoginRequest) {
        try {
            String username = authLoginRequest.username();
            String password = authLoginRequest.password();
            
            
            Authentication authentication = authenticate(username, password);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            String token = jwtUtils.createToken(authentication);
            
            return new AuthResponse(username, "Login exitoso", token, true);
        } catch (Exception e) {
            throw new BadCredentialsException(e.getMessage());
        }
    }

    public Authentication authenticate(String username, String password) {
        UserDetails userDetails = this.loadUserByUsername(username);
        
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Contraseña incorrecta para el usuario: " + username);
        }

        return new UsernamePasswordAuthenticationToken(
            username, 
            null, 
            userDetails.getAuthorities()
        );
    }









    public AuthResponse createUser(AuthCreateUserRequest request) {
        // Verificar si el usuario ya existe
        if (userRepository.findUserEntityByUsername(request.username()).isPresent()) {
            throw new IllegalArgumentException("El usuario ya existe");
        }

        // Obtener roles
        Set<RoleEntity> roles = roleRepository.findRoleEntitiesByRoleEnumIn(request.roles())
            .stream()
            .collect(Collectors.toSet());

        if (roles.isEmpty()) {
            throw new IllegalArgumentException("No se encontraron roles válidos");
        }

        // Obtener carrera
        EscuelaProfesional carrera = escuelaProfesionalRepository.findById(request.carreraId())
            .orElseThrow(() -> new IllegalArgumentException("Carrera no encontrada"));

        // Crear persona
        Persona persona = Persona.builder()
            .nombre(request.nombre())
            .apellido(request.apellido())
            .correoElectronico(request.correoElectronico())
            .direccion(request.direccion())
            .dni(request.dni())
            .nacionalidad(request.nacionalidad())
            .telefono(request.telefono())
            .build();

        personaRepository.save(persona);

        // Generar contraseña aleatoria
        String generatedPassword = generateRandomPassword();

        // Crear usuario
        UserEntity newUser = UserEntity.builder()
            .username(request.username())
            .password(passwordEncoder.encode(generatedPassword))
            .roles(roles)
            .isEnabled(true)
            .accountNoLocked(true)
            .accountNoExpired(true)
            .credentialNoExpired(true)
            .persona(persona)
            .build();

        userRepository.save(newUser);

        // Delegar la creación específica del rol Coordinador
        if (roles.stream().anyMatch(role -> role.getRoleEnum() == RoleEnum.COORDINADOR)) {
            coordinadorService.createCoordinador(persona, carrera);
        }

        // Enviar correo electrónico con la contraseña generada
        emailService.sendEmail(
            request.correoElectronico(),
            "Bienvenido al Sistema de Prácticas Pre-Profesionales",
            String.format(
                "Estimado(a) %s %s,%n%n" +
                "Le damos la bienvenida al Sistema de Prácticas Pre-Profesionales.%n%n" +
                "Sus credenciales de acceso son:%n" +
                "Usuario: %s%n" +
                "Contraseña: %s%n%n" +
                "Por su seguridad, le recomendamos cambiar su contraseña en su primer inicio de sesión.%n%n" +
                "Atentamente,%n" +
                "Equipo de Prácticas Pre-Profesionales",
                request.nombre(),
                request.apellido(),
                request.username(),
                generatedPassword
            )
        );

        // Crear autenticación
        Authentication authentication = new UsernamePasswordAuthenticationToken(
            request.username(), 
            null, 
            getAuthorities(newUser)
        );

        String token = jwtUtils.createToken(authentication);
        return new AuthResponse(request.username(), "Usuario creado exitosamente", token, true);
    }

 // Definir el método generateRandomPassword
 private String generateRandomPassword() {
    // Implementar lógica para generar una contraseña aleatoria
    return UUID.randomUUID().toString();
}









    private List<SimpleGrantedAuthority> getAuthorities(UserEntity user) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleEnum().name()));
            role.getPermissionList().forEach(permission -> 
                authorities.add(new SimpleGrantedAuthority(permission.getName())));
        });
        return authorities;
    }



   public List<UserEntity> getAllUsers() {
        return userRepository.findAllWithPersona(); // Using optimized query
    }
 





    public AuthResponse createDirectora(DirectoraCreateRequest request) {
        // Verificar usuario existente
        if (userRepository.findUserEntityByUsername(request.username()).isPresent()) {
            throw new IllegalArgumentException("El usuario ya existe");
        }

        // Crear persona
        Persona persona = Persona.builder()
            .nombre(request.nombre())
            .apellido(request.apellido())
            .correoElectronico(request.correoElectronico())
            .dni(request.dni())
            .telefono(request.telefono())
            .build();
        personaRepository.save(persona);

        // Generar contraseña
        String generatedPassword = generateRandomPassword();

        // Obtener rol DIRECTOR
        Set<RoleEntity> roles = Set.of(roleRepository.findByRoleEnum(RoleEnum.DIRECTOR)
            .orElseThrow(() -> new IllegalArgumentException("Rol DIRECTOR no encontrado")));

        // Obtener carrera
        EscuelaProfesional carrera = escuelaProfesionalRepository.findById(request.carreraId())
            .orElseThrow(() -> new IllegalArgumentException("Carrera no encontrada"));

        // Crear directora
        Directora directora = new Directora();
        directora.setPersona(persona);
        directora.setEscuelaProfesional(carrera);
        directora.setFirma(request.firma());
        directora.setSello(request.sello());
        directoraRepository.save(directora);

        // Crear usuario
        UserEntity user = UserEntity.builder()
            .username(request.username())
            .password(passwordEncoder.encode(generatedPassword))
            .roles(roles)
            .isEnabled(true)
            .accountNoLocked(true)
            .accountNoExpired(true)
            .credentialNoExpired(true)
            .persona(persona)
            .build();
        userRepository.save(user);

        // Enviar email con credenciales
        String emailText = String.format(
            "Estimada Directora %s %s,%n%n" +
            "Le damos la bienvenida al Sistema de Prácticas Pre-Profesionales.%n%n" +
            "Sus credenciales de acceso son:%n" +
            "Usuario: %s%n" +
            "Contraseña: %s%n%n" +
            "Por favor, cambie su contraseña al iniciar sesión.%n%n" +
            "Atentamente,%n" +
            "Equipo de Prácticas Pre-Profesionales",
            persona.getNombre(),
            persona.getApellido(),
            request.username(),
            generatedPassword
        );

        emailService.sendEmail(request.correoElectronico(), "Bienvenida al Sistema", emailText);
 
 // Crear autenticación
 Authentication authentication = new UsernamePasswordAuthenticationToken(
    user.getUsername(),
    null,
    getAuthorities(user)
);
SecurityContextHolder.getContext().setAuthentication(authentication);


        // Generar token
        String token = jwtUtils.createToken(authentication);
        return new AuthResponse(
            user.getUsername(),
            "Directora creada exitosamente",
            token,
            true
        );

        





















        








































        


    }










  public AuthResponse createPracticante(PracticanteCreateRequest request) {
        // Verificar usuario existente
        if (userRepository.findUserEntityByUsername(request.username()).isPresent()) {
            throw new IllegalArgumentException("El usuario ya existe");
        }

        // Crear persona
        Persona persona = Persona.builder()
            .nombre(request.nombre())
            .apellido(request.apellido())
            .correoElectronico(request.correoElectronico())
            .dni(request.dni())
            .telefono(request.telefono())
            .direccion(request.direccion())
            .sexo(request.sexo())
            .nacionalidad(request.nacionalidad())
            .build();
        personaRepository.save(persona);

        String generatedPassword = generateRandomPassword();

        // Obtener rol PRACTICANTE
        Set<RoleEntity> roles = Set.of(roleRepository.findByRoleEnum(RoleEnum.PRACTICANTE)
            .orElseThrow(() -> new IllegalArgumentException("Rol PRACTICANTE no encontrado")));

        // Crear usuario
        UserEntity user = UserEntity.builder()
            .username(request.username())
            .password(passwordEncoder.encode(generatedPassword))
            .roles(roles)
            .isEnabled(true)
            .accountNoLocked(true)
            .accountNoExpired(true)
            .credentialNoExpired(true)
            .persona(persona)
            .build();
        userRepository.save(user);

        // Obtener escuela y línea
        EscuelaProfesional escuela = escuelaProfesionalRepository.findById(request.escuelaId())
            .orElseThrow(() -> new IllegalArgumentException("Escuela no encontrada"));
        
        Linea linea = lineaRepository.findById(request.lineaId())
            .orElseThrow(() -> new IllegalArgumentException("Línea no encontrada"));

        // Crear practicante y relacionados
        practicanteService.createPracticante(
            persona,
            escuela,
            request.añoEstudio(),
            linea,
            request.codigo()
        );

        // Enviar email
        String emailText = String.format(
            "Estimado(a) Practicante %s %s,%n%n" +
            "Le damos la bienvenida al Sistema de Prácticas Pre-Profesionales.%n%n" +
            "Sus credenciales de acceso son:%n" +
            "Usuario: %s%n" +
            "Contraseña: %s%n%n" +
            "Por favor, cambie su contraseña al iniciar sesión.%n%n" +
            "Atentamente,%n" +
            "Equipo de Prácticas Pre-Profesionales",
            persona.getNombre(),
            persona.getApellido(),
            request.username(),
            generatedPassword
        );

        emailService.sendEmail(request.correoElectronico(), "Bienvenida al Sistema", emailText);

        // Autenticación y token
        Authentication authentication = new UsernamePasswordAuthenticationToken(
            user.getUsername(),
            null,
            getAuthorities(user)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtils.createToken(authentication);
        return new AuthResponse(
            user.getUsername(),
            "Practicante creado exitosamente",
            token,
            true
        );
    }


     
    public List<UserEntity> getAllPracticantes() {
        return userRepository.findByRoles_RoleEnum(RoleEnum.PRACTICANTE);
    }
}

















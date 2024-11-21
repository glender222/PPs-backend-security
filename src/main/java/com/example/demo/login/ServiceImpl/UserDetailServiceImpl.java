package com.example.demo.login.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
import com.example.demo.login.Entity.RoleEntity;
import com.example.demo.login.Entity.UserEntity;
import com.example.demo.login.Repository.RoleRepository;
import com.example.demo.login.Repository.UserRepository;
import com.example.demo.login.config.JwtUtils;

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findUserEntityByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no existe."));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        userEntity.getRoles()
                .forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));

        userEntity.getRoles().stream()
                .flatMap(role -> role.getPermissionList().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));

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

        // Crear usuario
        UserEntity newUser = UserEntity.builder()
            .username(request.username())
            .password(passwordEncoder.encode(request.password()))
            .roles(roles)
            .isEnabled(true)
            .accountNoLocked(true)
            .accountNoExpired(true)
            .credentialNoExpired(true)
            .build();

        userRepository.save(newUser);

        // Crear autenticación
        Authentication authentication = new UsernamePasswordAuthenticationToken(
            request.username(), 
            null, 
            getAuthorities(newUser)
        );

        String token = jwtUtils.createToken(authentication);
        return new AuthResponse(request.username(), "Usuario creado exitosamente", token, true);
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
}

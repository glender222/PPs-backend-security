package com.example.demo;


import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.entity.Persona;
import com.example.demo.login.Entity.PermissionEntity;
import com.example.demo.login.Entity.RoleEntity;
import com.example.demo.login.Entity.RoleEnum;
import com.example.demo.login.Entity.UserEntity;
import com.example.demo.login.Repository.UserRepository;
import com.example.demo.repository.PersonaRepository;
import com.example.demo.login.Repository.RoleRepository;
import com.example.demo.login.Repository.PermissionRepository;

@SpringBootApplication
public class PyApplication {

	public static void main(String[] args) {
		SpringApplication.run(PyApplication.class, args);
	}

	/*@Bean
	CommandLineRunner init(UserRepository userRepository, 
                      PersonaRepository personaRepository, 
                      RoleRepository roleRepository,
                      PermissionRepository permissionRepository,
                      PasswordEncoder passwordEncoder) {
		return args -> {
			try {
                // 1. Create all permissions
                Set<PermissionEntity> adminPermissions = new HashSet<>();

                adminPermissions.add(permissionRepository.findByName("CREAR CAMPUS")
                        .orElseGet(() -> permissionRepository.save(PermissionEntity.builder()
                                .name("CREAR CAMPUS")
                                .estado("ACTIVO")
                                .build())));

                adminPermissions.add(permissionRepository.findByName("LISTAR CAMPUS")
                        .orElseGet(() -> permissionRepository.save(PermissionEntity.builder()
                                .name("LISTAR CAMPUS")
                                .estado("ACTIVO")
                                .build())));

                adminPermissions.add(permissionRepository.findByName("CREAR FACULTAD")
                        .orElseGet(() -> permissionRepository.save(PermissionEntity.builder()
                                .name("CREAR FACULTAD")
                                .estado("ACTIVO")
                                .build())));

                adminPermissions.add(permissionRepository.findByName("CREAR ESCUELA")
                        .orElseGet(() -> permissionRepository.save(PermissionEntity.builder()
                                .name("CREAR ESCUELA")
                                .estado("ACTIVO")
                                .build())));

                // 2. Create and save admin role
                RoleEntity roleAdmin = roleRepository.findByRoleEnum(RoleEnum.ADMIN)
                        .orElseGet(() -> roleRepository.save(RoleEntity.builder()
                                .roleEnum(RoleEnum.ADMIN)
                                .permissionList(adminPermissions)
                                .build()));

                // 3. Create and save persona
                Persona adminPersona = personaRepository.save(Persona.builder()
                        .nombre("Admin")
                        .apellido("System")
                        .dni("00000000")
                        .correoElectronico("admin@system.com")
                        .telefono("000000000")
                        .build());

                // 4. Create and save admin user
                UserEntity userAdmin = userRepository.save(UserEntity.builder()
                        .username("ADMIN")
                        .password(passwordEncoder.encode("1234"))
                        .isEnabled(true)
                        .accountNoExpired(true)
                        .accountNoLocked(true)
                        .credentialNoExpired(true)
                        .roles(new HashSet<>(Set.of(roleAdmin)))
                        .persona(adminPersona)
                        .build());

            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }*/
}
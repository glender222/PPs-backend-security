package com.example.demo.Dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

import com.example.demo.login.Entity.RoleEnum;




public record AuthCreateUserRequest(
    @NotBlank String username,
    @NotEmpty List<RoleEnum> roles,
    @NotBlank String nombre,
     String sexo,
    @NotBlank String apellido,
    @Email @NotBlank String correoElectronico,
     String direccion,
    @NotBlank String dni,
     String nacionalidad,
    @NotBlank String telefono,
    @NotNull @Positive Long carreraId  
) {}

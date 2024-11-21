package com.example.demo.Dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

import com.example.demo.login.Entity.RoleEnum;




public record AuthCreateUserRequest(
    @NotBlank String username,
    @NotBlank String password,
    @NotEmpty List<RoleEnum> roles  // Cambiar a RoleEnum
) {}

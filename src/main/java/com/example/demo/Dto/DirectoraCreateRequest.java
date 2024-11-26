package com.example.demo.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DirectoraCreateRequest(
    @NotBlank String username,
    @NotBlank String nombre,
    @NotBlank String apellido,
    @Email @NotBlank String correoElectronico,
    @NotBlank String dni,
    @NotBlank String telefono,
    @NotNull @Positive Long carreraId,
    @NotBlank String firma,
    @NotBlank String sello
) {}

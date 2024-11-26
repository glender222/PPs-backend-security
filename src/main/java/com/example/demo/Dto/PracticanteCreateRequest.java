package com.example.demo.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PracticanteCreateRequest(
    @NotBlank String username,
    @NotBlank String nombre,
    @NotBlank String apellido,
    @Email @NotBlank String correoElectronico,
    @NotBlank String dni,
    @NotBlank String telefono,
    String direccion,
    String sexo,
    String nacionalidad,
    @NotBlank String codigo,
    @NotBlank String añoEstudio,
    @NotNull @Positive Long escuelaId,
    @NotNull @Positive Long lineaId
) {}
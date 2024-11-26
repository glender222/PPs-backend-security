package com.example.demo.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CartaPresentacionDto(

    // Datos de la empresa
    @NotBlank String ruc,
    @NotBlank String razonSocial,
    @NotBlank String direccion,
    @NotBlank String descripcion,

    // Datos del representante legal
    @NotBlank String nombreRepresentante,
    @NotBlank String apellidoRepresentante,
    @NotBlank String cargoRepresentante,
    @NotBlank String telefonoRepresentante,
    @Email @NotBlank String correoRepresentante,

    // Área de prácticas
    @NotBlank String areaPracticaNombre,
    String areaPracticaDescripcion

) {}
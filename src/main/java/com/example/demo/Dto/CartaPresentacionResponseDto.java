
package com.example.demo.Dto;

public record CartaPresentacionResponseDto(
    // Empresa info
    String ruc,
    String razonSocial,
    String direccion,
    String descripcion,
    
    // Representante info
    String nombreRepresentante,
    String apellidoRepresentante,
    String cargoRepresentante,
    String telefonoRepresentante,
    String correoRepresentante,
    
    // Area practica info
    String areaPracticaNombre,
    String areaPracticaDescripcion,
    
    // Status
    String estado
) {}
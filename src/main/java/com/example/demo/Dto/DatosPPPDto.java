package com.example.demo.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class DatosPPPDto {
    // Datos de la empresa
    @NotBlank(message = "La razón social es obligatoria")
    private String razonSocial;
    
    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;
    
    @NotBlank(message = "El RUC es obligatorio")
    @Pattern(regexp = "\\d{11}", message = "El RUC debe tener 11 dígitos")
    private String ruc;
    
    private String descripcion;

    // Datos del área de prácticas
    @NotBlank(message = "El nombre del área es obligatorio")
    private String nombreArea;
    private String descripcionArea;

    // Datos del representante
    @NotBlank(message = "El nombre del representante es obligatorio")
    private String nombreRepresentante;
    private String apellidoRepresentante;
    private String cargoRepresentante;
    private String telefonoRepresentante;
    
    @Email(message = "El correo electrónico no es válido")
    private String correoRepresentante;

    private String nombreLinea;
}

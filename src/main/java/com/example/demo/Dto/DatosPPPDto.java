package com.example.demo.Dto;

import lombok.Data;

@Data
public class DatosPPPDto {
   // Datos de la empresa
   private String razonSocial;
   private String direccion;
   private String ruc;
   private String descripcion;

   // Datos del área de prácticas
   private String nombreArea;
   private String descripcionArea;

   // Datos del representante
   private String nombreRepresentante;
   private String apellidoRepresentante;
   private String cargoRepresentante;
   private String telefonoRepresentante;
   private String correoRepresentante;

   private String nombreLinea;
}

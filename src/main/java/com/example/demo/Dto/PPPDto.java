package com.example.demo.Dto;

import lombok.Data;

import java.util.Date;

@Data
public class PPPDto {
    private Long id;
    private String estado;
    private Long practicanteEpId;
    private String lineaNombre; // Para el nombre de la línea
    private String razonSocial;
    private String ruc;
    private String direccion;
    private String nombreArea;
    private String descripcionArea;
    // Campos de representante
    private String nombreRepresentante;
    private String apellidoRepresentante;
    private String cargoRepresentante;
    private String telefonoRepresentante;
    private String correoRepresentante;
    private Integer horas;
    private String modalidad;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFin;
}

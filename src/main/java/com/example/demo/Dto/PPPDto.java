package com.example.demo.Dto;

import lombok.Data;

import java.util.Date;

@Data
public class PPPDto {
    private Long id;
    private String estado;
    private Long practicanteEpId;
    private String lineaNombre;
    private String razonSocial;
    private String ruc;
    private String direccion;
    private String nombreArea;
    private String descripcionArea;
    private String nombreRepresentante;
    private String cargoRepresentante;
    private Integer horas;
    private String modalidad;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFin;
}

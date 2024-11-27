package com.example.demo.Dto;

import java.util.List;

public class EscuelaLineaDto {
    private Long id; // ID de la escuela
    private String carrera; // Nombre de la carrera
    private List<LineaDto> lineasNombres; // Lista de objetos LineaDto con id y nombre
    private Long idFacultad; // ID de la facultad

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public List<LineaDto> getLineasNombres() {
        return lineasNombres;
    }

    public void setLineasNombres(List<LineaDto> lineasNombres) {
        this.lineasNombres = lineasNombres;
    }

    public Long getIdFacultad() {
        return idFacultad;
    }

    public void setIdFacultad(Long idFacultad) {
        this.idFacultad = idFacultad;
    }
}

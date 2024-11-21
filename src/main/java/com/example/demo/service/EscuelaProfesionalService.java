package com.example.demo.service;

import java.util.List;

import com.example.demo.Dto.EscuelaProfesionalDTO;


public interface EscuelaProfesionalService {
	EscuelaProfesionalDTO create(EscuelaProfesionalDTO escuelaDTO);
    EscuelaProfesionalDTO update(EscuelaProfesionalDTO escuelaDTO);
    void delete(Long id);
    EscuelaProfesionalDTO findById(Long id);
    List<EscuelaProfesionalDTO> findAll();
    List<EscuelaProfesionalDTO> findByFacultadId(Long facultadId);
}
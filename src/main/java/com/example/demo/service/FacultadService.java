package com.example.demo.service;

import java.util.List;

import com.example.demo.Dto.FacultadDTO;


public interface FacultadService {
	FacultadDTO create(FacultadDTO facultadDTO);
    FacultadDTO update(FacultadDTO facultadDTO);
    void delete(Long id);
    FacultadDTO findById(Long id);
    List<FacultadDTO> findAll();
}

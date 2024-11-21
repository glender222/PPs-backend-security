package com.example.demo.service;

import java.util.List;

import com.example.demo.Dto.CampusDTO;



public interface CampusService {
	CampusDTO create(CampusDTO campusDTO);
    CampusDTO update(CampusDTO campusDTO);
    void delete(Long id);
    CampusDTO findById(Long id);
    List<CampusDTO> findAll();
}

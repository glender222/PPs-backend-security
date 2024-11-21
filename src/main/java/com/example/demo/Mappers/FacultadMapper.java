package com.example.demo.Mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


import com.example.demo.Dto.FacultadDTO;

import com.example.demo.entity.Facultad;

@Component
public class FacultadMapper {

    private final ModelMapper modelMapper;

    public FacultadMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public FacultadDTO toDto(Facultad facultad) {
        return modelMapper.map(facultad, FacultadDTO.class);
    }

    public Facultad toEntity(FacultadDTO facultadDTO) {
        return modelMapper.map(facultadDTO, Facultad.class);
    }
}

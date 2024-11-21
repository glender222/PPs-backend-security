package com.example.demo.Mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.demo.Dto.EscuelaProfesionalDTO;
import com.example.demo.entity.EscuelaProfesional;

@Component
public class EscuelaProfesionalMapper {

 private final ModelMapper modelMapper;

    public EscuelaProfesionalMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public EscuelaProfesionalDTO toDto(EscuelaProfesional escuelaProfesional) {
        return modelMapper.map(escuelaProfesional, EscuelaProfesionalDTO.class);
    }

    public EscuelaProfesional toEntity(EscuelaProfesionalDTO escuelaProfesionalDTO) {
        return modelMapper.map(escuelaProfesionalDTO, EscuelaProfesional.class);
    }

}

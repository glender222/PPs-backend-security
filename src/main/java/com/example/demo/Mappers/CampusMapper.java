package com.example.demo.Mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.demo.Dto.CampusDTO;
import com.example.demo.entity.Campus;

@Component
public class CampusMapper {


    
    private final ModelMapper modelMapper;

    public CampusMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CampusDTO toDto(Campus campus) {
        return modelMapper.map(campus, CampusDTO.class);
    }

    public Campus toEntity(CampusDTO campusDTO) {
        return modelMapper.map(campusDTO, Campus.class);
    }
}

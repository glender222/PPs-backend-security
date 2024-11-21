package com.example.demo.Mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.demo.Dto.RoleDTO;
import com.example.demo.login.Entity.RoleEntity;

@Component
public class RoleMapper {
 

    private final ModelMapper modelMapper;

    public RoleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    // Método para convertir de RoleEntity a RoleDTO
    public RoleDTO toDto(RoleEntity roleEntity) {
        return modelMapper.map(roleEntity, RoleDTO.class);
    }

    // Método para convertir de RoleDTO a RoleEntity
    public RoleEntity toEntity(RoleDTO roleDTO) {
        return modelMapper.map(roleDTO, RoleEntity.class);
    }
}

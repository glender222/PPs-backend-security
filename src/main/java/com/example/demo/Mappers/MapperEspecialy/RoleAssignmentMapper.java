package com.example.demo.Mappers.MapperEspecialy;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.demo.Dto.RoleAssignmentDTO;
import com.example.demo.entity.EscuelaProfesional;
import com.example.demo.entity.Facultad;
import com.example.demo.login.Entity.PermissionEntity;
import com.example.demo.login.Entity.RoleEntity;

@Component
public class RoleAssignmentMapper {


     private final ModelMapper modelMapper;
    
    public RoleAssignmentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    
    public RoleAssignmentDTO toDto(RoleEntity role, Facultad facultad, EscuelaProfesional escuela) {
        RoleAssignmentDTO dto = new RoleAssignmentDTO();
        dto.setRoleEnum(role.getRoleEnum());
        dto.setFacultadId(facultad != null ? facultad.getId() : null);
        dto.setEscuelaId(escuela != null ? escuela.getId() : null);
        dto.setPermissions(role.getPermissionList().stream()
            .map(PermissionEntity::getName)
            .collect(Collectors.toSet()));
        return dto;
    }



    
}

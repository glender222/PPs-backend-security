package com.example.demo.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.Dto.RoleAssignmentDTO;
import com.example.demo.Mappers.MapperEspecialy.RoleAssignmentMapper;
import com.example.demo.entity.EscuelaProfesional;
import com.example.demo.entity.Facultad;
import com.example.demo.login.Entity.PermissionEntity;
import com.example.demo.login.Entity.RoleEntity;
import com.example.demo.login.Repository.RoleRepository;
import com.example.demo.repository.EscuelaProfesionalRepository;
import com.example.demo.repository.FacultadRepository;
import com.example.demo.login.Repository.PermissionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleAssignmentService {

    private final RoleAssignmentMapper roleAssignmentMapper;
    private final FacultadRepository facultadRepository;
    private final EscuelaProfesionalRepository escuelaProfesionalRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public RoleAssignmentDTO assignRole(RoleAssignmentDTO dto) {
        Facultad facultad = facultadRepository.findById(dto.getFacultadId())
            .orElseThrow(() -> new RuntimeException("Facultad not found"));
            
        EscuelaProfesional escuela = escuelaProfesionalRepository.findById(dto.getEscuelaId())
            .orElseThrow(() -> new RuntimeException("Escuela not found"));

        RoleEntity role = roleRepository.findByRoleEnum(dto.getRoleEnum())
            .orElseThrow(() -> new RuntimeException("Role not found"));

        // Validate escuela belongs to facultad
        if (!escuela.getFacultad().getId().equals(facultad.getId())) {
            throw new RuntimeException("Escuela does not belong to Facultad");
        }

          // Asignar permisos al rol
        Set<PermissionEntity> permissions = dto.getPermissions().stream()
            .map(permissionName -> permissionRepository.findByName(permissionName)
                .orElseThrow(() -> new RuntimeException("Permission not found: " + permissionName)))
            .collect(Collectors.toSet());
        role.setPermissionList(permissions);

        // Guardar cambios en la base de datos
        roleRepository.save(role);

        return roleAssignmentMapper.toDto(role, facultad, escuela);
    }
}

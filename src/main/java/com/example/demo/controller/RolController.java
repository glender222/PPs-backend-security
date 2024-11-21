package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.RoleDTO;
import com.example.demo.login.Entity.PermissionEntity;
import com.example.demo.login.Entity.RoleEntity;
import com.example.demo.service.RoleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/asignar")
@PreAuthorize("hasRole('ADMIN')") 
@RequiredArgsConstructor
public class RolController {
private final RoleService roleService;

@PostMapping("/{roleName}/permissions")
public ResponseEntity<RoleDTO> assignPermissionsToRole(
    @PathVariable String roleName, @RequestBody RoleDTO roleDTO) {

        // Validamos que el rol sea válido según el enum
        roleService.validateRoleName(roleName);
    
        // Obtenemos la lista de permisos desde el DTO
        List<String> permissions = roleDTO.getPermissions();
    
        // Llamamos al servicio para asignar los permisos al rol predefinido
        RoleEntity updatedRole = roleService.assignPermissionsToRole(roleName, permissions);
    
        // Convertimos la entidad actualizada en un DTO para devolverla como respuesta
        RoleDTO updatedRoleDTO = new RoleDTO();
        updatedRoleDTO.setRoleName(updatedRole.getRoleEnum().name());
        updatedRoleDTO.setPermissions(updatedRole.getPermissionList().stream()
                .map(PermissionEntity::getName)
                .collect(Collectors.toList()));
    
        return ResponseEntity.ok(updatedRoleDTO);
}

  @GetMapping
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        List<RoleDTO> roles = roleService.getAllRoles().stream()
                .map(role -> {
                    RoleDTO dto = new RoleDTO();
                    dto.setRoleName(role.getRoleEnum().name());
                    dto.setPermissions(role.getPermissionList().stream()
                            .map(PermissionEntity::getName)
                            .collect(Collectors.toList()));
                    return dto;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(roles);
    }




}
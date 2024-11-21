package com.example.demo.service;

import java.util.List;

import com.example.demo.Dto.RoleDTO;
import com.example.demo.login.Entity.RoleEntity;

public interface RoleService {
   RoleEntity createRole(RoleDTO roleDTO);
   RoleEntity assignPermissionsToRole(String roleName, List<String> permissions); // Asignar permisos a un rol
   List<RoleEntity> getAllRoles();  // Obtener todos los roles
   void validateRoleName(String roleName);
}

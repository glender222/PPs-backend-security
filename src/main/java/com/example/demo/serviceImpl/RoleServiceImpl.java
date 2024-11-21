package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.Dto.RoleDTO;
import com.example.demo.Mappers.RoleMapper;
import com.example.demo.login.Entity.PermissionEntity;
import com.example.demo.login.Entity.RoleEntity;
import com.example.demo.login.Entity.RoleEnum;
import com.example.demo.login.Repository.PermissionRepository;
import com.example.demo.login.Repository.RoleRepository;
import com.example.demo.service.RoleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{
    
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final RoleMapper roleMapper;
    // Método para validar el rol
    public void validateRoleName(String roleName) {
        try {
            RoleEnum.valueOf(roleName);  // Validamos si el rol ingresado es válido
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("El rol ingresado no es válido: " + roleName);
        }
    }



    @Override
    public RoleEntity createRole(RoleDTO roleDTO) {
         // Primero, validamos el nombre del rol
        validateRoleName(roleDTO.getRoleName());

        // Obtener permisos del repository según los nombres en el DTO
        Set<PermissionEntity> permissions = permissionRepository.findByNameIn(roleDTO.getPermissions())
                .stream()
                .collect(Collectors.toSet());

        // Utilizamos el RoleMapper para convertir el DTO en una entidad
        RoleEntity roleEntity = roleMapper.toEntity(roleDTO);

        // Ajustamos el campo RoleEnum y permisos
        roleEntity.setRoleEnum(RoleEnum.valueOf(roleDTO.getRoleName())); // Asignamos el RoleEnum correcto
        roleEntity.setPermissionList(permissions);                        // Asignamos la lista de permisos

        // Guardamos el rol en la base de datos
        return roleRepository.save(roleEntity);
    }

    @Override
    public List<RoleEntity> getAllRoles() {
        return roleRepository.findAll();
    }
    @Override
    public RoleEntity assignPermissionsToRole(String roleName, List<String> permissions) {
         // Validar el nombre del rol
    validateRoleName(roleName);

    // Buscar el rol en la base de datos por su RoleEnum
    RoleEntity roleEntity = roleRepository.findByRoleEnum(RoleEnum.valueOf(roleName))
            .orElseThrow(() -> new IllegalArgumentException("El rol no fue encontrado: " + roleName));

    // Buscar permisos en la base de datos según los nombres proporcionados
    Set<PermissionEntity> permissionsToAssign = permissionRepository.findByNameIn(permissions)
            .stream()
            .collect(Collectors.toSet());

    // Validar si todos los permisos se encontraron
    if (permissionsToAssign.size() != permissions.size()) {
        throw new IllegalArgumentException("Algunos permisos proporcionados no fueron encontrados en el sistema");
    }

    // Asignar los permisos al rol
    roleEntity.setPermissionList(permissionsToAssign);

    // Guardar el rol actualizado en la base de datos
    return roleRepository.save(roleEntity);

    }

}
    


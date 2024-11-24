package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Dto.RoleDTO;

import com.example.demo.login.Entity.RoleEntity;

import com.example.demo.service.RoleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{@Override
    public RoleEntity createRole(RoleDTO roleDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createRole'");
    }

    @Override
    public RoleEntity assignPermissionsToRole(String roleName, List<String> permissions) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'assignPermissionsToRole'");
    }

    @Override
    public List<RoleEntity> getAllRoles() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllRoles'");
    }

    @Override
    public void validateRoleName(String roleName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validateRoleName'");
    }
    
    

}
    


package com.example.demo.login.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.login.Entity.RoleEntity;
import com.example.demo.login.Entity.RoleEnum;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    List<RoleEntity> findRoleEntitiesByRoleEnumIn(List<RoleEnum> roleNames);
    Optional<RoleEntity> findByRoleEnum(RoleEnum roleEnum);
}

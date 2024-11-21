package com.example.demo.login.Repository;

import java.util.List;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.login.Entity.PermissionEntity;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {

    // Definir el método que acepte una lista de nombres y devuelva las entidades correspondientes
    Set<PermissionEntity> findByNameIn(List<String> names);

}

package com.example.demo.login.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.login.Entity.PermissionEntity;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {

    // Definir el método que acepte una lista de nombres y devuelva las entidades correspondientes

    Optional<PermissionEntity> findByName(String name);
}

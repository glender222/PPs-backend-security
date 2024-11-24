package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.EscuelaProfesional;

@Repository
public interface EscuelaProfesionalRepository extends JpaRepository<EscuelaProfesional, Long>{
    List<EscuelaProfesional> findByFacultadId(Long facultadId);

    
    @Query("SELECT e FROM EscuelaProfesional e " +
    "JOIN Coordinador c ON e.id = c.escuelaProfesional.id " +
    "JOIN UserEntity u ON c.persona.id = u.persona.id " +
    "JOIN u.roles r " +
    "WHERE r.id = :roleId")
List<EscuelaProfesional> findAllByRoleId(@Param("roleId") Long roleId);
}

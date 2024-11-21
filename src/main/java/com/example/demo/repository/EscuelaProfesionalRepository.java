package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.EscuelaProfesional;

@Repository
public interface EscuelaProfesionalRepository extends JpaRepository<EscuelaProfesional, Long>{
    List<EscuelaProfesional> findByFacultadId(Long facultadId);
}

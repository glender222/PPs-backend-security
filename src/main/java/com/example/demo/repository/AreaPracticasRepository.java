package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.AreaPracticas;

@Repository
public interface AreaPracticasRepository extends JpaRepository<AreaPracticas, Long>{
    Optional<AreaPracticas> findByNombre(String nombre);

}
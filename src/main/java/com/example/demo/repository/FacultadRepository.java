package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Facultad;

@Repository
public interface FacultadRepository extends JpaRepository<Facultad, Long> {
    // Método opcional para buscar por id de campus
    List<Facultad> findByCampusId(Long campusId);
    
    // Eliminar o corregir el método problemático findByFacultadId
    // Optional<Facultad> findById(Long id); // Este método ya viene por defecto de JpaRepository
}

package com.example.demo.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Practicante;

@Repository
public interface PracticanteRepository extends JpaRepository<Practicante, Long>{
     @Query("SELECT p FROM Practicante p WHERE p.persona.id = :personaId")
    Optional<Practicante> findByPersonaId(@Param("personaId") Long personaId);
}

package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.PPP;

@Repository
public interface PPPRepository extends JpaRepository<PPP, Long>{
    @Query("SELECT p FROM PPP p WHERE p.practicante_EP.practicante.persona.id = :personaId")
    Optional<PPP> findByPersonaId(@Param("personaId") Long personaId);
}
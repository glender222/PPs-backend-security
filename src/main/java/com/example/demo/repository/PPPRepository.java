package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.PPP;
import com.example.demo.entity.Practicante_EP;

@Repository
public interface PPPRepository extends JpaRepository<PPP, Long>{
    @Query("SELECT p FROM PPP p WHERE p.practicante_EP.practicante.persona.id = :personaId")
    Optional<PPP> findByPersonaId(@Param("personaId") Long personaId);

    List<PPP> findByLineaId(Long idLinea);


 // Método para verificar si existe PPP por practicante_EP
 @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PPP p WHERE p.practicante_EP = :practicanteEP")
 boolean existsByPracticante_EP(@Param("practicanteEP") Practicante_EP practicanteEP);
    
    // Método para buscar PPP por ID de persona
    @Query("SELECT p FROM PPP p WHERE p.practicante_EP.practicante.id = :personaId")
    Optional<PPP> findByPracticante_EP_Persona_Id(@Param("personaId") Long personaId);
    
    // Alias más corto para findByPracticante_EP_Practicante_Persona_Id
    

    List<PPP> findByEstado(String estado);


    @Query("SELECT p FROM PPP p WHERE p.practicante_EP = :practicanteEP")
    Optional<PPP> findByPracticante_EP(@Param("practicanteEP") Practicante_EP practicanteEP);

}
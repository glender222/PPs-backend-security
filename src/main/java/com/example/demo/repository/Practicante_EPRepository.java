package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Practicante_EP;

@Repository
public interface Practicante_EPRepository extends JpaRepository<Practicante_EP, Long> {
    @Query("SELECT pe FROM Practicante_EP pe LEFT JOIN FETCH pe.practicante WHERE pe.practicante.id = :practicanteId")
    Optional<Practicante_EP> findByPracticanteId(@Param("practicanteId") Long practicanteId);
}
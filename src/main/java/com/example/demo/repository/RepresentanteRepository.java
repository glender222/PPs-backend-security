package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Representante;
import com.example.demo.entity.Empresa;

import java.util.Optional;

@Repository
public interface RepresentanteRepository extends JpaRepository<Representante, Long>{
    Optional<Representante> findByEmpresa(Empresa empresa);
}
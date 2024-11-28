package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Empresa;
import com.example.demo.entity.Representante;

import java.util.Optional;
import java.util.List;

@Repository
public interface RepresentanteRepository extends JpaRepository<Representante, Long> {
    Optional<Representante> findFirstByEmpresaId(Long empresaId);
    Optional<Representante> findFirstByEmpresa(Empresa empresa);
    List<Representante> findByEmpresa(Empresa empresa);
    
    @Query("SELECT r FROM Representante r WHERE r.empresa.ruc = :ruc AND r.cargo = :cargo AND r.correo_elec = :correo")
    Optional<Representante> findByRucAndCargoAndCorreoElec(
        @Param("ruc") String ruc,
        @Param("cargo") String cargo,
        @Param("correo") String correo
    );
}
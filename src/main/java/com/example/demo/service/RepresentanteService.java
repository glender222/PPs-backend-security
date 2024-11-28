package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Representante;
import com.example.demo.entity.Empresa;
import com.example.demo.repository.RepresentanteRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RepresentanteService {
    private final RepresentanteRepository representanteRepository;

    @Transactional
    public Representante save(Representante representante) {
        try {
            // Verificar que tenga empresa asignada
            if (representante.getEmpresa() == null) {
                throw new RuntimeException("El representante debe tener una empresa asignada");
            }

            // Asegurar que la relación bidireccional esté establecida
            Empresa empresa = representante.getEmpresa();
            empresa.addRepresentante(representante);

            return representanteRepository.save(representante);
        } catch (Exception e) {
            log.error("Error al guardar representante: ", e);
            throw new RuntimeException("Error al guardar representante: " + e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public Representante findByEmpresaId(Long empresaId) {
        return representanteRepository.findFirstByEmpresaId(empresaId)
            .orElseThrow(() -> new RuntimeException("No se encontró representante para la empresa"));
    }
}
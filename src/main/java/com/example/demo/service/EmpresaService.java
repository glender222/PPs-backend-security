package com.example.demo.service;


import org.springframework.stereotype.Service;

import com.example.demo.entity.Empresa;
import com.example.demo.repository.EmpresaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class EmpresaService {
	private final EmpresaRepository empresaRepository;

    public Empresa findById(Long id) {
        return empresaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
    }

    public Empresa save(Empresa empresa) {
        return empresaRepository.save(empresa);
    }
}

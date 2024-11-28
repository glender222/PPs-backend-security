package com.example.demo.service;


import org.springframework.stereotype.Service;

import com.example.demo.entity.AreaPracticas;
import com.example.demo.repository.AreaPracticasRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AreaPracticasService {
	private final AreaPracticasRepository areaPracticasRepository;

    public AreaPracticas findById(Long id) {
        return areaPracticasRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Área de prácticas no encontrada"));
    }

    public AreaPracticas save(AreaPracticas area) {
        return areaPracticasRepository.save(area);
    }
}

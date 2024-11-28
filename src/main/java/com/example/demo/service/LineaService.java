package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Linea;
import com.example.demo.repository.LineaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LineaService {
	 private final LineaRepository lineaRepository;

    public Optional<Linea> findByNombre(String nombre) {
        return lineaRepository.findByNombre(nombre);
    }
}
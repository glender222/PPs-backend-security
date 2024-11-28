package com.example.demo.service;



import org.springframework.stereotype.Service;

import com.example.demo.entity.Representante;
import com.example.demo.repository.RepresentanteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RepresentanteService {
	  private final RepresentanteRepository representanteRepository;

    public Representante save(Representante representante) {
        return representanteRepository.save(representante);
    }
}
package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Coordinador;
import com.example.demo.entity.EscuelaProfesional;
import com.example.demo.entity.Persona;
import com.example.demo.repository.CoordinadorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CoordinadorService {
 private final CoordinadorRepository coordinadorRepository;

    public void createCoordinador(Persona persona, EscuelaProfesional escuelaProfesional) {
        Coordinador coordinador = new Coordinador();
        coordinador.setPersona(persona);
        coordinador.setEscuelaProfesional(escuelaProfesional);
        coordinadorRepository.save(coordinador);
    }
}

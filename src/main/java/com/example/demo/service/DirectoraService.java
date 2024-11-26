package com.example.demo.service;


import org.springframework.stereotype.Service;

import com.example.demo.entity.Directora;
import com.example.demo.entity.EscuelaProfesional;
import com.example.demo.entity.Persona;
import com.example.demo.repository.DirectoraRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class DirectoraService {
 private final DirectoraRepository directoraRepository;

    public void createDirectora(Persona persona, EscuelaProfesional escuelaProfesional, String firma, String sello) {
        Directora directora = new Directora();
        directora.setPersona(persona);
        directora.setEscuelaProfesional(escuelaProfesional);
        directora.setFirma(firma);
        directora.setSello(sello);
        directoraRepository.save(directora);
    }
}
package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Linea;
import com.example.demo.repository.LineaRepository;

import lombok.RequiredArgsConstructor;





@RestController
@RequestMapping("/lineas")
@CrossOrigin(origins = "http://localhost:4200")
@PreAuthorize("hasRole('COORDINADOR')")
@RequiredArgsConstructor
public class LineaController {
private final LineaRepository lineaRepository;

    @GetMapping
    public ResponseEntity<List<Linea>> getAllLineas() {
        List<Linea> lineas = lineaRepository.findAll();
        return ResponseEntity.ok(lineas);
    }
}

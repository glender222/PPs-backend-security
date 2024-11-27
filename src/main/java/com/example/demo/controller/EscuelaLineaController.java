package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.EscuelaLineaDto;
import com.example.demo.service.EscuelaLineaService;



@RestController
@RequestMapping("/api/escuelas-lineas")
@CrossOrigin(origins = "http://localhost:4200/")
@PreAuthorize("hasRole('SECRETARIA')")
public class EscuelaLineaController {

    @Autowired
    private EscuelaLineaService escuelaLineaService;

    // Listar todas las escuelas con las líneas y sus IDs
    @GetMapping
    public ResponseEntity<List<EscuelaLineaDto>> getAllEscuelasLineas() {
        List<EscuelaLineaDto> escuelas = escuelaLineaService.getEscuelasLineasConNombres();
        return ResponseEntity.ok(escuelas);
    }

    // Obtener una escuela específica con carrera y líneas
    @GetMapping("/{id}")
    public ResponseEntity<EscuelaLineaDto> getEscuelaById(@PathVariable Long id) {
        EscuelaLineaDto escuela = escuelaLineaService.getEscuelaById(id);
        return ResponseEntity.ok(escuela);
    }

}

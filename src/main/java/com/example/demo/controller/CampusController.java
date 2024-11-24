package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.CampusDTO;
import com.example.demo.Dto.EscuelaProfesionalDTO;
import com.example.demo.Dto.FacultadDTO;
import com.example.demo.service.CampusService;
import com.example.demo.service.EscuelaProfesionalService;
import com.example.demo.service.FacultadService;

@RestController
@RequestMapping("/mantener")
@CrossOrigin(origins = "http://localhost:4200")
@PreAuthorize("hasRole('ADMIN')") 
public class CampusController {

    private final CampusService campusService;
    private final FacultadService facultadService;
    private final EscuelaProfesionalService escuelaProfesionalService;

    public CampusController(CampusService campusService, FacultadService facultadService, EscuelaProfesionalService escuelaProfesionalService) {
        this.campusService = campusService;
        this.facultadService = facultadService;
        this.escuelaProfesionalService = escuelaProfesionalService;
    }

    // Endpoints for Campus
    @PostMapping("/campus")
    public ResponseEntity<CampusDTO> createCampus(@RequestBody CampusDTO campusDTO) {
        return ResponseEntity.ok(campusService.create(campusDTO));
    }

    @PutMapping("/campus/{id}")
    public ResponseEntity<CampusDTO> updateCampus(@PathVariable Long id, @RequestBody CampusDTO campusDTO) {
        campusDTO.setId(id);
        return ResponseEntity.ok(campusService.update(campusDTO));
    }

    @DeleteMapping("/campus/{id}")
    public ResponseEntity<Void> deleteCampus(@PathVariable Long id) {
        campusService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/campus/{id}")
    public ResponseEntity<CampusDTO> getCampusById(@PathVariable Long id) {
        return ResponseEntity.ok(campusService.findById(id));
    }

    @GetMapping("/campus")
    public ResponseEntity<List<CampusDTO>> getAllCampus() {
        return ResponseEntity.ok(campusService.findAll());
    }

    // Endpoints for Facultad
    @PostMapping("/facultad")
    public ResponseEntity<FacultadDTO> createFacultad(@RequestBody FacultadDTO facultadDTO) {
        return ResponseEntity.ok(facultadService.create(facultadDTO));
    }

    @PutMapping("/facultad/{id}")
    public ResponseEntity<FacultadDTO> updateFacultad(@PathVariable Long id, @RequestBody FacultadDTO facultadDTO) {
        facultadDTO.setId(id);
        return ResponseEntity.ok(facultadService.update(facultadDTO));
    }

    @DeleteMapping("/facultad/{id}")
    public ResponseEntity<Void> deleteFacultad(@PathVariable Long id) {
        facultadService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/facultad/{id}")
    public ResponseEntity<FacultadDTO> getFacultadById(@PathVariable Long id) {
        return ResponseEntity.ok(facultadService.findById(id));
    }

    @GetMapping("/facultad")
    public ResponseEntity<List<FacultadDTO>> getAllFacultades() {
        return ResponseEntity.ok(facultadService.findAll());
    }

    // Endpoints for EscuelaProfesional
    @PostMapping("/escuela")
    public ResponseEntity<EscuelaProfesionalDTO> createEscuela(@RequestBody EscuelaProfesionalDTO escuelaDTO) {
        return ResponseEntity.ok(escuelaProfesionalService.create(escuelaDTO));
    }

    @PutMapping("/escuela/{id}")
    public ResponseEntity<EscuelaProfesionalDTO> updateEscuela(@PathVariable Long id, @RequestBody EscuelaProfesionalDTO escuelaDTO) {
        escuelaDTO.setId(id);
        return ResponseEntity.ok(escuelaProfesionalService.update(escuelaDTO));
    }

    @DeleteMapping("/escuela/{id}")
    public ResponseEntity<Void> deleteEscuela(@PathVariable Long id) {
        escuelaProfesionalService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/escuela/{id}")
    public ResponseEntity<EscuelaProfesionalDTO> getEscuelaById(@PathVariable Long id) {
        return ResponseEntity.ok(escuelaProfesionalService.findById(id));
    }

    @GetMapping("/escuela")
    public ResponseEntity<List<EscuelaProfesionalDTO>> getAllEscuelas() {
        return ResponseEntity.ok(escuelaProfesionalService.findAll());
    }

    @GetMapping("/escuela/facultad/{facultadId}")
    public ResponseEntity<List<EscuelaProfesionalDTO>> getEscuelasByFacultadId(@PathVariable Long facultadId) {
        return ResponseEntity.ok(escuelaProfesionalService.findByFacultadId(facultadId));
    }
}



/*
 * peticiones postman 
 *  POST
 * http://localhost:8080/mantener/campus
 * 
 * {
 *  "sede" : "juliaca"
 *  }
 * 
 * http://localhost:8080/mantener/facultad
 * {
 *   "nombre": "Facultad de Ingeniería",
 *   "idCampus": 1
 *   }
 *
 * http://localhost:8080/mantener/escuela
 * {
 *   "carrera": "Ingeniería ambiental",
 *   "idFacultad": 1
 * } 
 *
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
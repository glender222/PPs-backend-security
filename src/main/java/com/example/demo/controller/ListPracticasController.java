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

import com.example.demo.entity.Persona;
import com.example.demo.service.ListPracticasService;



@RestController
@RequestMapping("/api/list-practicas")
@PreAuthorize("hasRole('SECRETARIA')")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ListPracticasController {


    @Autowired
    private ListPracticasService listPracticasService;

    /**
     * Endpoint para listar estudiantes por línea.
     *
     * @param idLinea El ID de la línea.
     * @return Una lista de objetos `Persona` correspondientes a los estudiantes.
     */
    @GetMapping("/linea/{idLinea}")
    public ResponseEntity<?> listarEstudiantesPorLinea(@PathVariable Long idLinea) {
        List<Persona> estudiantes = listPracticasService.listarEstudiantesPorLinea(idLinea);
        if (estudiantes.isEmpty()) {
            return ResponseEntity.status(204).body("No se encontraron estudiantes para la línea con ID: " + idLinea);
        }
        System.out.println("Estudiantes retornados: " + estudiantes);
        return ResponseEntity.ok(estudiantes);
    }


    /**
     * Endpoint para buscar un estudiante por su código.
     *
     * @param codigo El código del estudiante.
     * @return El objeto `Persona` correspondiente al estudiante.
     */
    @GetMapping("/buscar/{codigo}")
    public ResponseEntity<Persona> buscarPorCodigo(@PathVariable Long codigo) {
        Persona estudiante = listPracticasService.buscarPorCodigo(codigo);
        if (estudiante == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(estudiante);
    }


}

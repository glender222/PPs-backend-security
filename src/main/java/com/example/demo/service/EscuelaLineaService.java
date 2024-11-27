package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.Dto.EscuelaLineaDto;
import com.example.demo.Dto.LineaDto;
import com.example.demo.entity.EscuelaProfesional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
@Service
public class EscuelaLineaService {




 @PersistenceContext
    private EntityManager entityManager;

    // Obtener la escuela por ID con las líneas y sus IDs
    public EscuelaLineaDto getEscuelaById(Long id) {
        TypedQuery<EscuelaProfesional> query = entityManager.createQuery(
            "SELECT e FROM EscuelaProfesional e LEFT JOIN FETCH e.lineas WHERE e.id = :id",
            EscuelaProfesional.class
        );
        query.setParameter("id", id);

        EscuelaProfesional escuela = query.getResultStream().findFirst()
            .orElseThrow(() -> new EntityNotFoundException("Escuela no encontrada con ID: " + id));

        // Mapear a DTO
        EscuelaLineaDto dto = new EscuelaLineaDto();
        dto.setId(escuela.getId());
        dto.setCarrera(escuela.getCarrera());
        dto.setIdFacultad(escuela.getFacultad() != null ? escuela.getFacultad().getId() : null);

        // Mapear líneas a LineaDto (contiene solo id y nombre)
        List<LineaDto> lineasNombres = escuela.getLineas().stream()
            .map(linea -> {
                LineaDto lineaDto = new LineaDto();
                lineaDto.setId(linea.getId()); // Asignar el id de la línea
                lineaDto.setNombre(linea.getNombre()); // Asignar el nombre de la línea
                return lineaDto;
            })
            .collect(Collectors.toList());

        dto.setLineasNombres(lineasNombres);

        return dto;
    }

    // Listar todas las escuelas con sus líneas y los IDs de las líneas
    public List<EscuelaLineaDto> getEscuelasLineasConNombres() {
        TypedQuery<EscuelaProfesional> query = entityManager.createQuery(
            "SELECT e FROM EscuelaProfesional e LEFT JOIN FETCH e.lineas", 
            EscuelaProfesional.class
        );

        List<EscuelaProfesional> escuelas = query.getResultList();

        return escuelas.stream().map(escuela -> {
            EscuelaLineaDto dto = new EscuelaLineaDto();
            dto.setId(escuela.getId());
            dto.setCarrera(escuela.getCarrera());
            dto.setIdFacultad(escuela.getFacultad() != null ? escuela.getFacultad().getId() : null);

            // Convertir las líneas en LineaDto (con solo id y nombre)
            List<LineaDto> lineasNombres = escuela.getLineas().stream()
                .map(linea -> {
                    LineaDto lineaDto = new LineaDto();
                    lineaDto.setId(linea.getId());
                    lineaDto.setNombre(linea.getNombre());
                    return lineaDto;
                })
                .collect(Collectors.toList());

            dto.setLineasNombres(lineasNombres);

            return dto;
        }).collect(Collectors.toList());
    }
}

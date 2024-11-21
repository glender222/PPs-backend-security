package com.example.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EscuelaProfesionalDTO {
    private Long id;
    private String carrera;
    private Long idFacultad;
}

package com.example.demo.service;


import org.springframework.stereotype.Service;

import com.example.demo.entity.EscuelaProfesional;
import com.example.demo.entity.Persona;
import com.example.demo.entity.Practicante;
import com.example.demo.entity.Practicante_EP;
import com.example.demo.repository.PracticanteRepository;
import com.example.demo.repository.Practicante_EPRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PracticanteService {
	private final PracticanteRepository practicanteRepository;
    private final Practicante_EPRepository practicanteEPRepository;
    // private final PPPRepository pppRepository;

    public void createPracticante(
		Persona persona,
		 EscuelaProfesional escuela,
		  String añoEstudio,
		//    Linea linea,
		    String codigo) {
        // Crear Practicante
        Practicante practicante = new Practicante();
        practicante.setPersona(persona);
        practicante.setCodigo(codigo);
        practicante.setAñoEstudio(añoEstudio);
        practicanteRepository.save(practicante);

        // Crear PracticanteEP
        Practicante_EP practicanteEP = new Practicante_EP();
        practicanteEP.setPracticante(practicante);
        practicanteEP.setEscuelaProfesional(escuela);
        practicanteEPRepository.save(practicanteEP);

        // // Crear PPP
		// PPP ppp = new PPP();
        // ppp.setPracticante_EP(practicanteEP);
        // ppp.setLinea(linea);
        // ppp.setEstado("0");
        // ppp.setModalidad(null);  // Permitir null
        // ppp.setFechaInicio(null); // Permitir null
        // ppp.setFechaFin(null);   // Permitir null
        // ppp.setHoras(null);      // Permitir null
        // ppp.setJefeEmpresarial(null); // Permitir null
        // pppRepository.save(ppp);
    }
}
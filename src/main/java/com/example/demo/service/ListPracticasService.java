package com.example.demo.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.PPP;
import com.example.demo.entity.Persona;
import com.example.demo.entity.Practicante;
import com.example.demo.entity.Practicante_EP;
import com.example.demo.repository.PPPRepository;

@Service
public class ListPracticasService {
   @Autowired
    private PPPRepository pppRepository;

    /**
     * Lista los estudiantes asociados a una línea específica.
     *
     * @param idLinea El ID de la línea.
     * @return Una lista de objetos `Persona` correspondientes a los estudiantes.
     */
    public List<Persona> listarEstudiantesPorLinea(Long idLinea) {
        // Obtiene todas las prácticas asociadas a la línea
        List<PPP> practicas = pppRepository.findAll()
                                           .stream()
                                           .filter(ppp -> {
                                               System.out.println("ID Línea de la práctica: " + (ppp.getLinea() != null ? ppp.getLinea().getId() : "null"));
                                               return ppp.getLinea() != null && Objects.equals(ppp.getLinea().getId(), idLinea);
                                           })
                                           .collect(Collectors.toList());

        // Imprime las prácticas filtradas
        System.out.println("Prácticas filtradas para la línea " + idLinea + ": " + practicas);

        // Asegúrate de que cada práctica esté correctamente vinculada a un Practicante_EP y una Persona
        List<Persona> estudiantes = practicas.stream()
                                             .map(PPP::getPracticante_EP) // Obtiene el Practicante_EP relacionado
                                             .filter(Objects::nonNull) // Filtra nulos
                                             .map(Practicante_EP::getPracticante) // Obtiene el Practicante relacionado
                                             .filter(Objects::nonNull) // Filtra nulos
                                             .map(Practicante::getPersona) // Obtiene la Persona relacionada
                                             .filter(Objects::nonNull) // Filtra nulos
                                             .distinct() // Evita duplicados
                                             .collect(Collectors.toList());

        // Imprime los estudiantes
        System.out.println("Estudiantes obtenidos para la línea " + idLinea + ": " + estudiantes);

        return estudiantes;
    }




    /**
     * Busca un estudiante por su código.
     *
     * @param codigo El código del estudiante.
     * @return El objeto `Persona` correspondiente al estudiante o null si no se encuentra.
     */
    public Persona buscarPorCodigo(Long codigo) {
        return pppRepository.findAll()
                            .stream()
                            .map(PPP::getPracticante_EP) // Obtiene el Practicante_EP asociado
                            .filter(practicante -> practicante != null && Objects.equals(practicante.getPracticante().getId(), codigo)) // Verifica el código del Practicante
                            .map(Practicante_EP::getPracticante) // Obtiene el Practicante
                            .filter(Objects::nonNull) // Asegura que no sea nulo
                            .map(Practicante::getPersona) // Obtiene la Persona asociada
                            .findFirst()
                            .orElse(null); // Retorna la primera coincidencia o null si no encuentra
    }
}

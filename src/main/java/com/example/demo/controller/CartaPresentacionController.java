package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.Dto.CartaPresentacionDto;
import com.example.demo.Dto.CartaPresentacionResponseDto;
import com.example.demo.entity.AreaPracticas;
import com.example.demo.entity.Empresa;
import com.example.demo.entity.PPP;
import com.example.demo.entity.Representante;
import com.example.demo.login.Entity.UserEntity;
import com.example.demo.login.Repository.UserRepository;
import com.example.demo.repository.AreaPracticasRepository;
import com.example.demo.repository.EmpresaRepository;
import com.example.demo.repository.PPPRepository;
import com.example.demo.repository.RepresentanteRepository;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cartapresentacion")
@PreAuthorize("hasRole('PRACTICANTE')") 

@RequiredArgsConstructor
public class CartaPresentacionController {
    private final PPPRepository pppRepository;
    private final EmpresaRepository empresaRepository;
    private final RepresentanteRepository representanteRepository;
    private final AreaPracticasRepository areaPracticasRepository;
    private final UserRepository userRepository;

    @PutMapping("/comenzarcarta")
    @PreAuthorize("hasRole('PRACTICANTE')")
    public ResponseEntity<?> actualizarCartaPresentacion(
            @Valid @RequestBody CartaPresentacionDto dto,
            Authentication authentication) {
        try {
            // Obtener el usuario autenticado
            UserEntity user = userRepository.findByUsername(authentication.getName())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            // Obtener la PPP del practicante
            PPP ppp = pppRepository.findByPersonaId(user.getPersona().getId())
                    .orElseThrow(() -> new RuntimeException("PPP no encontrada"));

            // Validar que no exista una carta previa
            if (!"0".equals(ppp.getEstado())) {
                return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Ya existe una carta de presentación para este practicante");
            }

            // Verificar o crear la empresa
            Empresa empresa = empresaRepository.findByRuc(dto.ruc())
                    .orElseGet(() -> {
                        Empresa nuevaEmpresa = new Empresa();
                        nuevaEmpresa.setRuc(dto.ruc());
                        nuevaEmpresa.setRazonSocial(dto.razonSocial());
                        nuevaEmpresa.setDireccion(dto.direccion());
                        nuevaEmpresa.setDescripcion(dto.descripcion());
                        return empresaRepository.save(nuevaEmpresa);
                    });

            // Crear representante legal y asociarlo a la empresa
            Representante representante = new Representante();
            representante.setNombre(dto.nombreRepresentante());
            representante.setApellido(dto.apellidoRepresentante());
            representante.setCargo(dto.cargoRepresentante());
            representante.setTelefono(dto.telefonoRepresentante());
            representante.setCorreo_elec(dto.correoRepresentante()); // Usa el setter correcto
            representante.setEmpresa(empresa);
            representanteRepository.save(representante);

            // Crear o buscar área de prácticas
            AreaPracticas areaPracticas = areaPracticasRepository.findByNombre(dto.areaPracticaNombre())
                    .orElseGet(() -> {
                        AreaPracticas nuevaArea = new AreaPracticas();
                        nuevaArea.setNombre(dto.areaPracticaNombre());
                        nuevaArea.setDescripcion(dto.areaPracticaDescripcion());
                        return areaPracticasRepository.save(nuevaArea);
                    });

            // Actualizar PPP
            ppp.setEmpresa(empresa);
            ppp.setArea_practicas(areaPracticas); // Usa el setter correcto
            ppp.setEstado("1");
            pppRepository.save(ppp);

            return ResponseEntity.ok("Carta de presentación actualizada correctamente");
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    @GetMapping("/micarta")
    @PreAuthorize("hasRole('PRACTICANTE')")
    public ResponseEntity<?> obtenerCartaPresentacion(Authentication authentication) {
        try {
            // Obtener el usuario autenticado
            UserEntity user = userRepository.findByUsername(authentication.getName())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            // Obtener la PPP del practicante
            PPP ppp = pppRepository.findByPersonaId(user.getPersona().getId())
                    .orElseThrow(() -> new RuntimeException("PPP no encontrada"));

            if (ppp.getEmpresa() == null || ppp.getArea_practicas() == null) {
                return ResponseEntity.notFound().build();
            }

            // Obtener el representante de la empresa
            Representante representante = representanteRepository.findByEmpresa(ppp.getEmpresa())
                    .orElseThrow(() -> new RuntimeException("Representante no encontrado"));

            CartaPresentacionResponseDto response = new CartaPresentacionResponseDto(
                ppp.getEmpresa().getRuc(),
                ppp.getEmpresa().getRazonSocial(),
                ppp.getEmpresa().getDireccion(),
                ppp.getEmpresa().getDescripcion(),
                representante.getNombre(),
                representante.getApellido(),
                representante.getCargo(),
                representante.getTelefono(),
                representante.getCorreo_elec(),
                ppp.getArea_practicas().getNombre(),
                ppp.getArea_practicas().getDescripcion(),
                ppp.getEstado()
            );

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al obtener la carta de presentación: " + e.getMessage());
        }
    }

}

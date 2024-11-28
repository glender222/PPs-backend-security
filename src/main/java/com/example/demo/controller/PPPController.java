package com.example.demo.controller;

import org.springframework.security.core.Authentication;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.DatosPPPDto;
import com.example.demo.Dto.PPPDto;
import com.example.demo.login.Entity.UserEntity;
import com.example.demo.login.Repository.UserRepository;
import com.example.demo.service.PPPService;

import lombok.RequiredArgsConstructor;
 

@RestController
@RequestMapping("/ppp")
@RequiredArgsConstructor
@PreAuthorize("hasRole('PRACTICANTE')")
@CrossOrigin(origins = "http://localhost:4200")
public class PPPController {
   private final PPPService pppService;
    private final UserRepository userRepository;


 @PostMapping("/comenzar")
    @PreAuthorize("hasRole('PRACTICANTE')")
    public ResponseEntity<?> comenzarPPP(Authentication authentication) {
        try {
            UserEntity user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            
            PPPDto ppp = pppService.comenzarPPP(user.getPersona().getId());
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of(
                    "mensaje", "PPP iniciada exitosamente",
                    "ppp", ppp
                ));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/estado")
    @PreAuthorize("hasRole('PRACTICANTE')")
    public ResponseEntity<?> obtenerEstadoPPP(Authentication authentication) {
        try {
            UserEntity user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            
            // Verificar si existe una PPP
            boolean existePPP = pppService.existePPPActiva(user.getPersona().getId());
            if (!existePPP) {
                return ResponseEntity.ok(Map.of(
                    "existePPP", false,
                    "mensaje", "No existe una PPP activa para este practicante"
                ));
            }

            PPPDto ppp = pppService.obtenerPPPPorPersonaId(user.getPersona().getId());
            return ResponseEntity.ok(Map.of(
                "existePPP", true,
                "ppp", ppp,
                "estado", ppp.getEstado()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/verificar")
    @PreAuthorize("hasRole('PRACTICANTE')")
    public ResponseEntity<?> verificarPPPActiva(Authentication authentication) {
        try {
            UserEntity user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            
            boolean existePPPActiva = pppService.existePPPActiva(user.getPersona().getId());
            return ResponseEntity.ok(Map.of(
                "existePPPActiva", existePPPActiva
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", e.getMessage()));
        }
    }




 @PostMapping("/guardar-datos")  // Removemos el {pppId} del path
    @PreAuthorize("hasRole('PRACTICANTE')")
    public ResponseEntity<?> guardarDatosPPP(
            @RequestBody DatosPPPDto datos,
            Authentication authentication) {
        try {
            // 1. Verificar usuario
            UserEntity user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            
            // 2. Obtener PPP activa del practicante
            PPPDto ppp = pppService.obtenerPPPPorPersonaId(user.getPersona().getId());
            
            // 3. Validar datos completos
            if (datos == null) {
                return ResponseEntity.badRequest()
                    .body(Map.of("error", "No se recibieron datos"));
            }
            
            // 4. Guardar datos usando el ID de la PPP obtenida
            PPPDto pppActualizada = pppService.guardarDatosPPP(ppp.getId(), datos);
            return ResponseEntity.ok(Map.of(
                "mensaje", "Datos guardados exitosamente",
                "ppp", pppActualizada,
                "estado", pppActualizada.getEstado()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(Map.of("error", e.getMessage()));
        }
    }
}


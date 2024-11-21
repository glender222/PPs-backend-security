package com.example.demo.login.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api")
public class SimpleController {

    @PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
    @GetMapping("/admin")
    public ResponseEntity<String> adminEndpoint() {
        return ResponseEntity.ok("Acceso concedido para ADMINISTRADOR");
    }

    @PreAuthorize("hasAuthority('ROLE_PRACTICANTE')")
    @GetMapping("/practicante")
    public ResponseEntity<String> practicanteEndpoint() {
        return ResponseEntity.ok("Acceso concedido para PRACTICANTE");
    }

    @PreAuthorize("hasAuthority('ROLE_SECRETARIA')")
    @GetMapping("/secretaria")
    public ResponseEntity<String> secretariaEndpoint() {
        return ResponseEntity.ok("Acceso concedido para SECRETARIA");
    }

    @PreAuthorize("hasAuthority('ROLE_COORDINADOR')")
    @GetMapping("/coordinador")
    public ResponseEntity<String> coordinadorEndpoint() {
        return ResponseEntity.ok("Acceso concedido para COORDINADOR");
    }

    @PreAuthorize("hasAuthority('ROLE_TUTOR_ACADEMICO')")
    @GetMapping("/tutor")
    public ResponseEntity<String> tutorEndpoint() {
        return ResponseEntity.ok("Acceso concedido para TUTOR_ACADEMICO");
    }

    @PreAuthorize("hasAuthority('ROLE_DIRECTORA')")
    @GetMapping("/directora")
    public ResponseEntity<String> directoraEndpoint() {
        return ResponseEntity.ok("Acceso concedido para DIRECTORA");
    }
}
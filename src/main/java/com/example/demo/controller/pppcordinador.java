package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.PPPDto;
import com.example.demo.login.Enum.EstadoPPP;
import com.example.demo.service.PPPService;

import lombok.RequiredArgsConstructor;




@RestController
@RequestMapping("/pppcordinador")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class pppcordinador {
private final PPPService pppService; 

 @GetMapping("/pendientes")
    @PreAuthorize("hasRole('COORDINADOR')")
    public ResponseEntity<?> listarPPPPendientes() {
        try {
            List<PPPDto> ppps = pppService.obtenerPPPPorEstado(EstadoPPP.PENDIENTE.getValor());
            if (ppps.isEmpty()) {
                return ResponseEntity.ok(Map.of("mensaje", "No hay PPPs pendientes"));
            }
            return ResponseEntity.ok(ppps);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/aprobar/{id}")
    @PreAuthorize("hasRole('COORDINADOR')")
    public ResponseEntity<?> aprobarPPP(@PathVariable Long id) {
        if (!pppService.validarDatosCompletos(id)) {
            return ResponseEntity.badRequest()
                .body(Map.of("error", "Datos incompletos para aprobar"));
        }


        try {
            pppService.actualizarEstadoPPP(id, EstadoPPP.APROBADA.getValor());
            return ResponseEntity.ok(Map.of(
                "mensaje", "PPP aprobada exitosamente",
                "estado", EstadoPPP.APROBADA.getValor()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/rechazar/{id}")
    @PreAuthorize("hasRole('COORDINADOR')")
    public ResponseEntity<?> rechazarPPP(@PathVariable Long id) {
        try {
            pppService.actualizarEstadoPPP(id, EstadoPPP.SIN_CARTA.getValor());
            return ResponseEntity.ok(Map.of(
                "mensaje", "PPP rechazada",
                "estado", EstadoPPP.SIN_CARTA.getValor()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(Map.of("error", e.getMessage()));
        }
    }
}

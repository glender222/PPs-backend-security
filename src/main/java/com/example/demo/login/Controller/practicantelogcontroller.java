package com.example.demo.login.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.AuthResponse;
import com.example.demo.Dto.PracticanteCreateRequest;
import com.example.demo.Dto.UserResponseDto;
import com.example.demo.login.ServiceImpl.UserDetailServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@RestController
@RequestMapping("/practicantelogin")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class practicantelogcontroller {
    private final UserDetailServiceImpl userDetailService;

    
 @PostMapping("/createpracticante")
 @PreAuthorize("hasRole('COORDINADOR')")
    public ResponseEntity<AuthResponse> createPracticante(@RequestBody @Valid PracticanteCreateRequest request) {
        try {
            log.debug("Creando nuevo practicante: {}", request.username());
            AuthResponse response = userDetailService.createPracticante(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            log.error("Error creando practicante: {}", e.getMessage());
            return ResponseEntity.badRequest()
                .body(new AuthResponse(null, "Error: " + e.getMessage(), null, false));
        }

    }
    @GetMapping("/listarpracticante")
    @PreAuthorize("hasRole('COORDINADOR')")
    public ResponseEntity<List<UserResponseDto>> getAllPracticantes() {
        try {
            List<UserResponseDto> practicantes = userDetailService.getAllPracticantes().stream()
                .map(user -> new UserResponseDto(
                    user.getUsername(),
                    user.getPersona().getNombre(),
                    user.getPersona().getApellido(),
                    user.getPersona().getCorreoElectronico(),
                    user.getPersona().getDni()
                ))
                .collect(Collectors.toList());
            
            return ResponseEntity.ok(practicantes);
        } catch (Exception e) {
            log.error("Error obteniendo practicantes: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
    }

    





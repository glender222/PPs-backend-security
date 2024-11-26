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
import com.example.demo.Dto.DirectoraCreateRequest;
import com.example.demo.Dto.UserResponseDto;
import com.example.demo.login.ServiceImpl.UserDetailServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/users")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ContrrollerActors {
    private final UserDetailServiceImpl userDetailService;



    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        try {
            List<UserResponseDto> users = userDetailService.getAllUsers().stream()
                .map(user -> new UserResponseDto(  // FIXED: Correct class name
                    user.getUsername(),
                    user.getPersona().getNombre(),
                    user.getPersona().getApellido(),
                    user.getPersona().getCorreoElectronico(),
                    user.getPersona().getDni()
                ))
                .collect(Collectors.toList());
            
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            log.error("Error fetching users: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }



@PostMapping("/createdirectora")
@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AuthResponse> createDirectora(@RequestBody @Valid DirectoraCreateRequest request) {
        try {
            AuthResponse response = userDetailService.createDirectora(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(new AuthResponse(null, "Error: " + e.getMessage(), null, false));
        }
    }






}

package com.example.demo.login.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.AuthCreateUserRequest;
import com.example.demo.Dto.AuthLoginRequest;
import com.example.demo.Dto.AuthResponse;
import com.example.demo.login.ServiceImpl.UserDetailServiceImpl;

import jakarta.validation.Valid;
import org.springframework.security.authentication.BadCredentialsException;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserDetailServiceImpl userDetailService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthLoginRequest userRequest) {
        try {
            AuthResponse response = this.userDetailService.loginUser(userRequest);
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new AuthResponse(null, "Credenciales inválidas", null, false));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new AuthResponse(null, "Error del servidor", null, false));
        }
    }

    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid AuthCreateUserRequest request) {
        try {
            log.debug("Registering new user: {}", request.username());
            AuthResponse response = userDetailService.createUser(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            log.error("Error registering user: {}", e.getMessage());
            return ResponseEntity.badRequest()
                .body(new AuthResponse(null, "Error al crear usuario: " + e.getMessage(), null, false));
        }
    }
}

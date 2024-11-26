package com.example.demo.Dto;

public record UserResponseDto (
    String username,
    String nombre,
    String apellido,
    String correoElectronico,
    String dni
) {}
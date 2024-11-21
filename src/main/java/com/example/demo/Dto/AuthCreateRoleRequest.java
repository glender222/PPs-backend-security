package com.example.demo.Dto;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Size;

@Validated
public record  AuthCreateRoleRequest (
    @Size(max = 7, message = "The user cannot have more than 3 roles") List<String> roleListName) {

}

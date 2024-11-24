package com.example.demo.controller;



import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.RoleAssignmentDTO;

import com.example.demo.service.RoleAssignmentService;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/asignar")
@PreAuthorize("hasRole('ADMIN')") 
@RequiredArgsConstructor
public class RolController {
 private final RoleAssignmentService roleAssignmentService;

    @PostMapping("/rol")
    public ResponseEntity<RoleAssignmentDTO> assignRole(@RequestBody RoleAssignmentDTO request) {
        RoleAssignmentDTO assignedRole = roleAssignmentService.assignRole(request);
        return ResponseEntity.ok(assignedRole);
    }


}








/*
 * http://localhost:8080/asignar/rol
 * {
 *   "roleEnum": "COORDINADOR",
 *   "facultadId": 1,
 *   "escuelaId": 1,
 *   "permissions": [
 *       "GESTIONAR CURSOS",
 *       "VER ESTUDIANTES"  ]
 * }
 * 
 * 
 * 
 * 
 */
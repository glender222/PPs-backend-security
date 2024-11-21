package com.example.demo.Dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {
 

    private String roleName;        // Nombre del rol (e.g., ADMIN, SECRETARIA)
    private List<String> permissions;
}

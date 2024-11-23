package com.example.demo.Dto;

import java.util.Set;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleAssignmentDTO {


    private com.example.demo.login.Entity.RoleEnum roleEnum;
    private Long facultadId;
    private Long escuelaId;
    private Set<String> permissions;
}

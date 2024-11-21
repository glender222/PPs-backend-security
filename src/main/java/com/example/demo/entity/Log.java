package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "logs")
public class Log {

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "log_seq_gen", sequenceName = "log_seq_gen", allocationSize = 1)
    private Long id;

    @Column(name = "nombre", length = 250)
    private String nombre;

    @Column(name = "estado", length = 1)
    private String estado;
    
   
}


package com.example.demo.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "linea")
public class Linea {

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "linea_seq_gen", sequenceName = "linea_seq_gen", allocationSize = 1)
    
    private Long id;

    @Column(name = "nombre", length = 250)
    private String nombre;

    @Column(name = "estado", length = 1)
    private String estado;
    
    @Column(name = "Nota_max", length = 1)
    private String nota_max;
    
    @OneToOne(mappedBy = "linea")
    @JsonIgnore
    private Tutores tutores;
    
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "linea")
   	@JsonIgnore
   	private Set<PPP> practicas;
}


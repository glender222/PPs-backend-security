package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@EqualsAndHashCode(callSuper=false)

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Data
@Table(name = "representante")
public class Representante {

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "representante_seq_gen", sequenceName = "representante_seq_gen", allocationSize = 1)
    private Long id;

    @Column(name = "cargo", length = 215)
    private String cargo;
    
    @Column(name = "nombre", length = 215)
    private String nombre;
    
    @Column(name = "apellido", length = 215)
    private String apellido;

    @Column(name = "telefono", length = 215)
    private String telefono;

    @Column(name = "correo_elec", length = 1)
    private String correo_elec;

    @ManyToOne
    @JoinColumn(name ="id_empresa", referencedColumnName ="id", nullable = false)
    @JsonIgnore
    private Empresa empresa;
    
}

package com.example.demo.entity;

import java.util.Set;

import com.example.demo.login.Entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder // Agregar esta anotación
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "persona")
public class Persona {

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "persona_seq_gen", sequenceName = "persona_seq_gen", allocationSize = 1)
    private Long id;

    @Column(name = "nombre", length = 250)
    private String nombre;

    @Column(name = "apellido", length = 250)
    private String apellido;

    @Column(name = "dni", length = 15)
    private String dni;

    @Column(name = "telefono", length = 15)
    private String telefono;

    @Column(name = "correo_electronico", length = 210)
    private String correoElectronico;

    @Column(name = "direccion", length = 210)
    private String direccion;

    @Column(name = "sexo", length = 1)
    private String sexo;

    @Column(name = "nacionalidad", length = 250)
    private String nacionalidad;

    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Tutores tutor;


    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Directora directora;

  
    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Practicante practicante;


    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "persona")
	@JsonIgnore
	private Set<UserEntity> userEntities;

    
    
    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<PPP> practicas;


    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Coordinador> coordinadores;
}

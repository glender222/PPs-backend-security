package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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
@Table(name = "directoras")
public class Directora {
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "director_seq_gen", sequenceName = "director_seq_gen", allocationSize = 1)
    private Long id;

	
	@Column(name = "Firma")
	private String Firma;
	
	@Column(name = "Sello")
	private String Sello;
	
	@ManyToOne
	@JoinColumn(name="id_EP", referencedColumnName = "id", nullable = false)
	@JsonIgnore
	private EscuelaProfesional escuelaProfesional;
	
	@OneToOne
	@JoinColumn(name = "idpersona", referencedColumnName = "id", nullable = false)
	@JsonIgnore
	private Persona persona;


}


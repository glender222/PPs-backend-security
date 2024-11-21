package com.example.demo.entity;

import java.util.Date;
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
@Table(name = "programacion")
public class Programacion {

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "programacion_seq_gen", sequenceName = "programacion_seq_gen", allocationSize = 1)
    private Long id;

    @Column(name = "semana")
    private Integer semana;

    @Column(name = "fecha_evaluacion")
    private Date fechaEvaluacion;

    @Column(name = "link", length = 250)
    private String link;

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "programacion")
	@JsonIgnore
	private Set<Rubros> rubros;

    @ManyToOne
    @JoinColumn(name = "idppp", nullable = false)
    @JsonIgnore
    private PPP ppp;
}

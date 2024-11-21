package com.example.demo.entity;

import java.util.Set;

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
@Table(name = "escuela_profesional")
public class EscuelaProfesional {

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "escuelaprof_seq_gen", sequenceName = "escuelaprof_seq_gen", allocationSize = 1)
    private Long id;
	
    @Column(name = "carrera", length = 250)
    private String carrera;

    @ManyToOne
    @JoinColumn(name = "id_facultad", nullable = false)
    @JsonIgnore
    private Facultad facultad;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "escuelaProfesional")
    @JsonIgnore
    private Set<Directora> directoras;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "escuelaProfesional")
    @JsonIgnore
    private Set<Tutores> tutores;
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "escuelaProfesional")
	@JsonIgnore
	private Set<Practicante_EP> practicante_EP;
}

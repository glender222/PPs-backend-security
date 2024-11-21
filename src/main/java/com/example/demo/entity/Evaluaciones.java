package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@EqualsAndHashCode(callSuper=false)

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Data
@Table(name = "evaluaciones")
public class Evaluaciones {

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "evaluaciones_seq_gen", sequenceName = "evaluaciones_seq_gen", allocationSize = 1)
    private Long id;

    @Column(name = "nota_empresarial")
    private int notaEmpresarial;

    @Column(name = "nota_tutor")
    private int notaTutor;

    @Column(name = "recomendaciones")
    private String recomendaciones;
    
    @Column(name = "estado")
    private String estado;

    @Column(name = "fecha_evaluacion")
    private Date fechaEvaluacion;

    @ManyToOne
	@JoinColumn(name="id_detalleEvaluacion", nullable = false)
	private DetalleEvaluaciones detalleEvaluaciones ;
    
    @ManyToOne
	@JoinColumn(name="id_PPP", nullable = false)
	private PPP ppp ;
}

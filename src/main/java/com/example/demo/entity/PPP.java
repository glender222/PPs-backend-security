package com.example.demo.entity;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "practicas")
public class PPP {

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "ppp_seq_gen", sequenceName = "ppp_seq_gen", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "horas")
    private Integer horas;


    @NotNull
    @Column(name = "modalidad", length = 215)
    private String modalidad;

    @NotNull
    @Column(name = "estado", length = 1)
    private String estado;

    
    @ManyToOne
    @JoinColumn(name = "id_empresa", nullable = true)
    @JsonIgnore
    private Empresa empresa;

    
    @ManyToOne
    @JoinColumn(name = "id_areap", nullable = true)
    @JsonIgnore
    private AreaPracticas area_practicas;
   

    
    @ManyToOne
    @JoinColumn(name = "idtutor", nullable = true)
    @JsonIgnore
    private Tutores tutores;

    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_practicante_EP", nullable = false)
    @JsonIgnore
    private Practicante_EP practicante_EP;


    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_jefe_empresarial", nullable = false)
    @JsonIgnore
    private JefeEmpresarial jefeEmpresarial;
   
   

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)  
    @Column(name = "fecha_inicio")
    private Date fechaInicio;


    @NotNull
    @Temporal(TemporalType.TIMESTAMP)  
    @Column(name = "fecha_fin")
    private Date fechaFin;

  
    
    @ManyToOne
    @JoinColumn(name = "idlinea", nullable = true)
    @JsonIgnore
    private Linea linea;

    
    @ManyToOne
    @JoinColumn(name = "idpersona_supervisor", referencedColumnName = "id", nullable = true)
    @JsonIgnore
    private Persona persona;

     // ppp uniendose con rubros tiene que ser cambiado 

   



// Nuevo enlaze para enlazar ppp con programacion 

@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "ppp")
@JsonIgnore
private Set<Programacion> programacions;





    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "practicas")
	@JsonIgnore
	private Set<Documentacion> documentacion;
}

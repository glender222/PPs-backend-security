package com.example.demo.entity;

import java.util.Date;
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
@Table(name = "practicas")
public class PPP {

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "ppp_seq_gen", sequenceName = "ppp_seq_gen", allocationSize = 1)
    private Long id;

    @Column(name = "horas")
    private Integer horas;

    @Column(name = "modalidad", length = 215)
    private String modalidad;

    @Column(name = "estado", length = 1)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_empresa", nullable = false)
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

    

    @ManyToOne
    @JoinColumn(name = "id_practicante_EP", nullable = false)
    @JsonIgnore
    private Practicante_EP practicante_EP;

    @ManyToOne
    @JoinColumn(name = "id_jefe_empresarial", nullable = false)
    @JsonIgnore
    private JefeEmpresarial jefeEmpresarial;
   
   


    @Temporal(TemporalType.TIMESTAMP)  
    @Column(name = "fecha_inicio")
    private Date fechaInicio;

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

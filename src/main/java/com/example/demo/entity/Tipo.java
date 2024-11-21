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
@Table(name = "tipo")
public class Tipo {

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "tipo_seq_gen", sequenceName = "tipo_seq_gen", allocationSize = 1)
    private Long id;

    @Column(name = "nombre", length = 250)
    private String nombre;

    @Column(name = "porcentaje")
    private Integer porcentaje;
    
    @Column(name = "estado", length = 1)
    private String estado;
    
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "tipo")
	@JsonIgnore
	private Set<Rubros> rubross;
    
}

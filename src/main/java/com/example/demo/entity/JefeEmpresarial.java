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
@Table(name = "jefe_empresarial")
public class JefeEmpresarial {

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "jefeempr_seq_gen", sequenceName = "jefeempr_seq_gen", allocationSize = 1)
    private Long id;
    
    @OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_persona", referencedColumnName = "id")
	private Persona persona;
    
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "jefeEmpresarial")
   	@JsonIgnore
   	private Set<PPP> ppps;

}

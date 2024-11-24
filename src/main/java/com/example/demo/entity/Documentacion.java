package com.example.demo.entity;

import java.util.Date;

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
@Table(name = "documentacion")
public class Documentacion {
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "documentacion_seq_gen", sequenceName = "documentacion_seq_gen", allocationSize = 1)
    private Long id;
   

    @Lob
    private byte[] archivo;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "estado", length = 1)
    private String estado;

    @Column(name = "fecha_generada")
    private Date fechaGenerada;

    @Transient
    private String tipoArchivo;

    // Método para verificar el tipo de archivo permitido
    public boolean esFormatoValido() {
        return tipoArchivo != null && 
              (tipoArchivo.equalsIgnoreCase("application/pdf") || 
               tipoArchivo.equalsIgnoreCase("application/msword") || 
               tipoArchivo.equalsIgnoreCase("application/vnd.openxmlformats-officedocument.wordprocessingml.document"));}







               

    @ManyToOne
    @JoinColumn(name = "id_tipo_documento", nullable = false)
    @JsonIgnore
    private TipoDocumento tipo_documento;
    
    @ManyToOne
    @JoinColumn(name = "idppp", nullable = false)
    @JsonIgnore
    private PPP practicas;

    
}

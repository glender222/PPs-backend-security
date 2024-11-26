package com.example.demo.controller;


import java.util.Date;
import java.util.List;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Documentacion;
import com.example.demo.service.DocumentacionService;
import com.example.demo.service.PPPService;
import com.example.demo.service.Practicante_EPService;
import com.example.demo.service.TipoDocumentoService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/documentos")
@PreAuthorize("hasRole('PRACTICANTE')") 
@CrossOrigin(origins = {"http://localhost:4200"})
public class DocumentacionController {

  @Autowired
    private DocumentacionService documentacionService;

    @Autowired
    private TipoDocumentoService tipoDocumentoService;

 

    @Autowired
    private  Practicante_EPService practicante_EPService;

    @GetMapping
    public ResponseEntity<List<Documentacion>> readAll() {
        try {
            List<Documentacion> documentaciones = documentacionService.readAll();
            if (documentaciones.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(documentaciones, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/subir-archivo")
    public ResponseEntity<?> crear(@RequestParam("archivo") MultipartFile archivo,
                                    @RequestParam("nombre") String nombre,
                                    @RequestParam("id_tipo_documento") Long idTipoDocumento) {
        try {
            String tipoArchivo = archivo.getContentType();
            if (tipoArchivo == null || 
               (!tipoArchivo.equalsIgnoreCase("application/pdf") && 
                !tipoArchivo.equalsIgnoreCase("application/msword") && 
                !tipoArchivo.equalsIgnoreCase("application/vnd.openxmlformats-officedocument.wordprocessingml.document"))) {
                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                                     .body("Solo se permiten archivos en formato PDF o Word (.doc, .docx).");
            }

            Documentacion documentacion = new Documentacion();
            documentacion.setNombre(nombre);
            documentacion.setTipo_documento(tipoDocumentoService.read(idTipoDocumento));
            documentacion.setArchivo(archivo.getBytes());
            documentacion.setEstado("A");
            documentacion.setFechaGenerada(new Date());

            Documentacion guardada = documentacionService.create(documentacion);
            return new ResponseEntity<>(guardada, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al procesar la solicitud: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<Documentacion> crear(@Valid @RequestBody Documentacion documentacion) {
        try {
            Documentacion c = documentacionService.create(documentacion);
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Documentacion> getDocumentacionId(@PathVariable("id") Long id) {
        try {
            Documentacion c = documentacionService.read(id);
            return new ResponseEntity<>(c, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delDocumentacion(@PathVariable("id") Long id) {
        try {
            documentacionService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el documento.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDocumentacion(@PathVariable("id") Long id, @Valid @RequestBody Documentacion cat) {
        try {
            Documentacion c = documentacionService.read(id);
            if (c.getId() > 0) {
                return new ResponseEntity<>(documentacionService.update(cat), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el documento.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}/archivo")
    public ResponseEntity<byte[]> obtenerArchivo(@PathVariable Long id) {
        try {
            Documentacion documentacion = documentacionService.read(id);
            if (documentacion == null) {
                return ResponseEntity.notFound().build();
            }

            String tipoArchivo = "application/pdf"; 
            return ResponseEntity.ok()
                                 .header("Content-Disposition", "attachment; filename=\"" + documentacion.getNombre() + "\"")
                                 .header("Content-Type", tipoArchivo)
                                 .body(documentacion.getArchivo());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
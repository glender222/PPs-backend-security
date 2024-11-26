package com.example.demo.controller;

import java.util.Date;
import java.util.List;

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
import com.example.demo.entity.PPP;
import com.example.demo.entity.Practicante_EP;
import com.example.demo.service.DocumentacionService;
import com.example.demo.service.PPPService;
import com.example.demo.service.Practicante_EPService;
import com.example.demo.service.TipoDocumentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tipodocumentaciones")
@PreAuthorize("hasRole('PRACTICANTE') or hasRole('COORDINADOR')")
@CrossOrigin(origins = {"http://localhost:4200"})

public class TipoDocumentoController {

  
    @Autowired
    private DocumentacionService documentacionService;

    @Autowired
    private TipoDocumentoService tipoDocumentoService;

    @Autowired
    private PPPService pppService;

    @Autowired
    private Practicante_EPService practicante_EPService;

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
                                   @RequestParam("id_tipo_documento") Long idTipoDocumento,
                                   @RequestParam("practicanteId") Long practicanteId,
                                   @RequestParam("practicaId") Long practicaId) {
        try {
            if (archivo == null || archivo.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                     .body("Debe enviar un archivo válido.");
            }

            String tipoArchivo = archivo.getContentType();
            if (tipoArchivo == null ||
               (!tipoArchivo.equalsIgnoreCase("application/pdf") &&
                !tipoArchivo.equalsIgnoreCase("application/msword") &&
                !tipoArchivo.equalsIgnoreCase("application/vnd.openxmlformats-officedocument.wordprocessingml.document"))) {
                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                                     .body("Solo se permiten archivos en formato PDF o Word (.doc, .docx).");
            }

            // Obtener la práctica y el practicante
            PPP practica = pppService.read(practicaId);
            Practicante_EP practicante = practicante_EPService.read(practicanteId);

            if (practica == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Práctica no encontrada");
            }

            if (practicante == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Practicante no encontrado");
            }

            Documentacion documentacion = new Documentacion();
            documentacion.setTipo_documento(tipoDocumentoService.read(idTipoDocumento));
            documentacion.setArchivo(archivo.getBytes());
            documentacion.setEstado("A");
            documentacion.setFechaGenerada(new Date());
            documentacion.setPracticas(practica); 

            Documentacion guardada = documentacionService.create(documentacion);
            return new ResponseEntity<>(guardada, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al procesar la solicitud: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<Documentacion> crear(@Valid @RequestBody Documentacion documentacion) {
        try {
            Documentacion guardada = documentacionService.create(documentacion);
            return new ResponseEntity<>(guardada, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Documentacion> getDocumentacionId(@PathVariable("id") Long id) {
        try {
            Documentacion documentacion = documentacionService.read(id);
            if (documentacion == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(documentacion, HttpStatus.OK);
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
    public ResponseEntity<?> updateDocumentacion(@PathVariable("id") Long id, @Valid @RequestBody Documentacion documentacion) {
        try {
            Documentacion existente = documentacionService.read(id);
            if (existente == null) {
                return new ResponseEntity<>("Documento no encontrado.", HttpStatus.NOT_FOUND);
            }

            documentacion.setId(id);
            Documentacion actualizado = documentacionService.update(documentacion);
            return new ResponseEntity<>(actualizado, HttpStatus.OK);
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

            String tipoArchivo = "application/pdf"; // Cambia según el tipo real del archivo si es necesario.
            return ResponseEntity.ok()
                                 .header("Content-Disposition", "attachment; filename=\"documento_" + id + "\"")
                                 .header("Content-Type", tipoArchivo)
                                 .body(documentacion.getArchivo());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}


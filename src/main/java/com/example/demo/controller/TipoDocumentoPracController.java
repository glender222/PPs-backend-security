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
import com.example.demo.service.DocumentacionService;
import com.example.demo.service.TipoDocumentoService;




@RestController
@RequestMapping("/tipodocumentacionesPRAC")
@PreAuthorize("hasRole('PRACTICANTE') ")
@CrossOrigin(origins = {"http://localhost:4200"})

public class TipoDocumentoPracController {
@Autowired
private TipoDocumentoService tipodocumentoService;
@Autowired
private DocumentacionService documentacionService;
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

    // Subir un archivo
    @PostMapping("/subir-archivo")
    public ResponseEntity<?> crear(@RequestParam("archivo") MultipartFile archivo,
                                    @RequestParam("id_tipo_documento") Long idTipoDocumento,
                                    @RequestParam("idppp") Long idPpp) {
        try {
            // Verificar que el archivo no esté vacío
            if (archivo.isEmpty()) {
                return ResponseEntity.badRequest().body("El archivo no puede estar vacío.");
            }

            // Crear la entidad Documentacion
            Documentacion documentacion = new Documentacion();
            documentacion.setTipo_documento(tipodocumentoService.read(idTipoDocumento)); // Obtener tipo_documento
            documentacion.setPracticas(new PPP(idPpp)); // Asociar PPP usando el nuevo constructor
            documentacion.setArchivo(archivo.getBytes()); // Guardar archivo como bytes
            documentacion.setEstado("A");
            documentacion.setFechaGenerada(new Date());

            // Validar tipo de archivo
            String tipoArchivo = archivo.getContentType();
            documentacion.setTipoArchivo(tipoArchivo);
            if (!documentacion.esFormatoValido()) {
                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                                     .body("Solo se permiten archivos en formato PDF o Word (.doc, .docx).");
            }

            // Guardar el documento
            Documentacion guardada = documentacionService.create(documentacion);
            return new ResponseEntity<>(guardada, HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error al procesar la solicitud: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Crear un documento sin archivo
    @PostMapping("/crear")
    public ResponseEntity<Documentacion> crear(@RequestBody Documentacion documentacion) {
        try {
            Documentacion c = documentacionService.create(documentacion);
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener un documento por ID
    @GetMapping("/{id}")
    public ResponseEntity<Documentacion> getDocumentacionId(@PathVariable("id") Long id) {
        try {
            Documentacion c = documentacionService.read(id);
            return new ResponseEntity<>(c, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar un documento por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delDocumentacion(@PathVariable("id") Long id) {
        try {
            documentacionService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el documento.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar un documento por ID
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDocumentacion(@PathVariable("id") Long id, @RequestBody Documentacion cat) {
        try {
            Documentacion c = documentacionService.read(id);
            if (c != null) {
                return new ResponseEntity<>(documentacionService.update(cat), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el documento.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener el archivo de un documento por ID
    @GetMapping("/{id}/archivo")
    public ResponseEntity<byte[]> obtenerArchivo(@PathVariable Long id) {
        try {
            Documentacion documentacion = documentacionService.read(id);
            if (documentacion == null) {
                return ResponseEntity.notFound().build();
            }

            // Asignar el tipo de archivo (por ahora, se asume que siempre será PDF)
            String tipoArchivo = "application/pdf"; 
            return ResponseEntity.ok()
                                 .header("Content-Disposition", "attachment; filename=\"" + documentacion.getArchivo() + "\"")
                                 .header("Content-Type", tipoArchivo)
                                 .body(documentacion.getArchivo());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}

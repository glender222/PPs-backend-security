package com.example.demo.controller;

import java.util.List;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.TipoDocumento;
import com.example.demo.service.TipoDocumentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tipodocumentos")
@CrossOrigin(origins = "http://localhost:4200/")
public class TipoDocumentoController {
	@Autowired
	private TipoDocumentoService tipodocumentoService;
	
	@GetMapping
	public ResponseEntity<List<TipoDocumento>> readAll(){
		try {
			List<TipoDocumento> TipoDocumentos = tipodocumentoService.readAll();
			if(TipoDocumentos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(TipoDocumentos, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@PostMapping
	public ResponseEntity<TipoDocumento> crear(@Valid @RequestBody TipoDocumento cat){
		try {
			TipoDocumento c = tipodocumentoService.create(cat);
			return new ResponseEntity<>(c, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<TipoDocumento> getTipoDocumentoId(@PathVariable("id") Long id){
		try {
			TipoDocumento c = tipodocumentoService.read(id);
			return new ResponseEntity<>(c, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<TipoDocumento> delTipoDocumento(@PathVariable("id") Long id){
		try {
			tipodocumentoService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> updateTipoDocumento(@PathVariable("id") Long id, @Valid @RequestBody TipoDocumento cat){

			TipoDocumento c = tipodocumentoService.read(id);
			if(c.getId()>0) {
				return new ResponseEntity<>(tipodocumentoService.update(cat), HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}		
		
	}
}

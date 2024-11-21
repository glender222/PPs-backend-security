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

import com.example.demo.entity.Documentacion;
import com.example.demo.service.DocumentacionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/documentaciones")
@CrossOrigin(origins = "http://localhost:4200/")
public class DocumentacionController {
	@Autowired
	private DocumentacionService documentacionService;
	
	@GetMapping
	public ResponseEntity<List<Documentacion>> readAll(){
		try {
			List<Documentacion> Documentacions = documentacionService.readAll();
			if(Documentacions.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(Documentacions, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@PostMapping
	public ResponseEntity<Documentacion> crear(@Valid @RequestBody Documentacion cat){
		try {
			Documentacion c = documentacionService.create(cat);
			return new ResponseEntity<>(c, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<Documentacion> getDocumentacionId(@PathVariable("id") Long id){
		try {
			Documentacion c = documentacionService.read(id);
			return new ResponseEntity<>(c, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Documentacion> delDocumentacion(@PathVariable("id") Long id){
		try {
			documentacionService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> updateDocumentacion(@PathVariable("id") Long id, @Valid @RequestBody Documentacion cat){

			Documentacion c = documentacionService.read(id);
			if(c.getId()>0) {
				return new ResponseEntity<>(documentacionService.update(cat), HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}		
		
	}
}

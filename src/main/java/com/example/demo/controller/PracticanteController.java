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

import com.example.demo.entity.Practicante;
import com.example.demo.service.PracticanteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/practicantes")
@CrossOrigin(origins = "http://localhost:4200/")
public class PracticanteController {
	@Autowired
	private PracticanteService practicanteService;
	
	@GetMapping
	public ResponseEntity<List<Practicante>> readAll(){
		try {
			List<Practicante> Practicantes = practicanteService.readAll();
			if(Practicantes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(Practicantes, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@PostMapping
	public ResponseEntity<Practicante> crear(@Valid @RequestBody Practicante cat){
		try {
			Practicante c = practicanteService.create(cat);
			return new ResponseEntity<>(c, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<Practicante> getPracticanteId(@PathVariable("id") Long id){
		try {
			Practicante c = practicanteService.read(id);
			return new ResponseEntity<>(c, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Practicante> delPracticante(@PathVariable("id") Long id){
		try {
			practicanteService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> updatePracticante(@PathVariable("id") Long id, @Valid @RequestBody Practicante cat){

			Practicante c = practicanteService.read(id);
			if(c.getId()>0) {
				return new ResponseEntity<>(practicanteService.update(cat), HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}		
		
	}
}

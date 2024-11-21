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

import com.example.demo.entity.Practicante_EP;
import com.example.demo.service.Practicante_EPService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/practicante_EPes")
@CrossOrigin(origins = "http://localhost:4200/")
public class Practicante_EPController {
	@Autowired
	private Practicante_EPService practicante_EPService;
	
	@GetMapping
	public ResponseEntity<List<Practicante_EP>> readAll(){
		try {
			List<Practicante_EP> Practicante_EPs = practicante_EPService.readAll();
			if(Practicante_EPs.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(Practicante_EPs, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@PostMapping
	public ResponseEntity<Practicante_EP> crear(@Valid @RequestBody Practicante_EP cat){
		try {
			Practicante_EP c = practicante_EPService.create(cat);
			return new ResponseEntity<>(c, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<Practicante_EP> getPracticante_EPId(@PathVariable("id") Long id){
		try {
			Practicante_EP c = practicante_EPService.read(id);
			return new ResponseEntity<>(c, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Practicante_EP> delPracticante_EP(@PathVariable("id") Long id){
		try {
			practicante_EPService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> updatePracticante_EP(@PathVariable("id") Long id, @Valid @RequestBody Practicante_EP cat){

			Practicante_EP c = practicante_EPService.read(id);
			if(c.getId()>0){
				return new ResponseEntity<>(practicante_EPService.update(cat), HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}		
		
	}
}

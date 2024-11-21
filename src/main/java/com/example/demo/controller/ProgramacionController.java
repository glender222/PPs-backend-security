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

import com.example.demo.entity.Programacion;
import com.example.demo.service.ProgramacionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/programaciones")
@CrossOrigin(origins = "http://localhost:4200/")
public class ProgramacionController {
	@Autowired
	private ProgramacionService programacionService;
	
	@GetMapping
	public ResponseEntity<List<Programacion>> readAll(){
		try {
			List<Programacion> Programacions = programacionService.readAll();
			if(Programacions.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(Programacions, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@PostMapping
	public ResponseEntity<Programacion> crear(@Valid @RequestBody Programacion cat){
		try {
			Programacion c = programacionService.create(cat);
			return new ResponseEntity<>(c, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<Programacion> getProgramacionId(@PathVariable("id") Long id){
		try {
			Programacion c = programacionService.read(id);
			return new ResponseEntity<>(c, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Programacion> delProgramacion(@PathVariable("id") Long id){
		try {
			programacionService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> updateProgramacion(@PathVariable("id") Long id, @Valid @RequestBody Programacion cat){

			Programacion c = programacionService.read(id);
			if(c.getId()>0) {
				return new ResponseEntity<>(programacionService.update(cat), HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}		
		
	}
}

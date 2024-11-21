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

import com.example.demo.entity.AreaPracticas;
import com.example.demo.service.AreaPracticasService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/areapracticas")
@CrossOrigin(origins = "http://localhost:4200/")
public class AreaPracticasController {
	@Autowired
	private AreaPracticasService areapracticasService;
	
	@GetMapping
	public ResponseEntity<List<AreaPracticas>> readAll(){
		try {
			List<AreaPracticas> AreaPracticass = areapracticasService.readAll();
			if(AreaPracticass.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(AreaPracticass, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@PostMapping
	public ResponseEntity<AreaPracticas> crear(@Valid @RequestBody AreaPracticas cat){
		try {
			AreaPracticas c = areapracticasService.create(cat);
			return new ResponseEntity<>(c, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<AreaPracticas> getAreaPracticasId(@PathVariable("id") Long id){
		try {
			AreaPracticas c = areapracticasService.read(id);
			return new ResponseEntity<>(c, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<AreaPracticas> delAreaPracticas(@PathVariable("id") Long id){
		try {
			areapracticasService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> updateAreaPracticas(@PathVariable("id") Long id, @Valid @RequestBody AreaPracticas cat){

			AreaPracticas c = areapracticasService.read(id);
			if(c.getId()>0) {
				return new ResponseEntity<>(areapracticasService.update(cat), HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}		
		
	}
}

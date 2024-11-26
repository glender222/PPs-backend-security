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

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Documentacion;
import com.example.demo.entity.PPP;
import com.example.demo.entity.TipoDocumento;
import com.example.demo.service.DocumentacionService;
import com.example.demo.service.TipoDocumentoService;




@RestController
@RequestMapping("/tipodocumentacioneprac")
@PreAuthorize("hasRole('PRACTICANTE') ")
@CrossOrigin(origins = {"http://localhost:4200"})
public class DocumentacionPracController {
 
@Autowired
	private TipoDocumentoService tipodocumentoService;
	
	@GetMapping("/tipodocumentoss")
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


}

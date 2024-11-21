package com.example.demo.service;

import java.util.List;
 

import com.example.demo.entity.Linea;


public interface LineaService {
	Linea create(Linea c);
	Linea update(Linea c);
	void delete(Long id);
	Linea read(Long id);
	List<Linea> readAll();
}
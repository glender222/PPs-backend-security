package com.example.demo.service;

import java.util.List;
 

import com.example.demo.entity.Documentacion;


public interface DocumentacionService {
	Documentacion create(Documentacion c);
	Documentacion update(Documentacion c);
	void delete(Long id);
	Documentacion read(Long id);
	List<Documentacion> readAll();
}
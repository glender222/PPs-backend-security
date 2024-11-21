package com.example.demo.service;

import java.util.List;
 

import com.example.demo.entity.Programacion;


public interface ProgramacionService {
	Programacion create(Programacion c);
	Programacion update(Programacion c);
	void delete(Long id);
	Programacion read(Long id);
	List<Programacion> readAll();
}
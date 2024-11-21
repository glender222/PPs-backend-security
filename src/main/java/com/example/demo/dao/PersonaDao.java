package com.example.demo.dao;

import java.util.List;


import com.example.demo.entity.Persona;


public interface PersonaDao {
	Persona create(Persona c);
	Persona update(Persona c);
	void delete(Long id);
	Persona read(Long id);
	List<Persona> readAll();
}

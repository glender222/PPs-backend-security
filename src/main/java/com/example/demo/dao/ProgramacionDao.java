package com.example.demo.dao;

import java.util.List;


import com.example.demo.entity.Programacion;


public interface ProgramacionDao {
	Programacion create(Programacion c);
	Programacion update(Programacion c);
	void delete(Long id);
	Programacion read(Long id);
	List<Programacion> readAll();
}
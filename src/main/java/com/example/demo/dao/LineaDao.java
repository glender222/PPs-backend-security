package com.example.demo.dao;

import java.util.List;


import com.example.demo.entity.Linea;


public interface LineaDao {
	Linea create(Linea c);
	Linea update(Linea c);
	void delete(Long id);
	Linea read(Long id);
	List<Linea> readAll();
}
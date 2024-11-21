package com.example.demo.service;

import java.util.List;
 

import com.example.demo.entity.Rubros;


public interface RubrosService {
	Rubros create(Rubros c);
	Rubros update(Rubros c);
	void delete(Long id);
	Rubros read(Long id);
	List<Rubros> readAll();
}
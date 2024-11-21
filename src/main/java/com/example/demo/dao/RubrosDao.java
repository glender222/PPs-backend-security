package com.example.demo.dao;

import java.util.List;


import com.example.demo.entity.Rubros;


public interface RubrosDao {
	Rubros create(Rubros c);
	Rubros update(Rubros c);
	void delete(Long id);
	Rubros read(Long id);
	List<Rubros> readAll();
}
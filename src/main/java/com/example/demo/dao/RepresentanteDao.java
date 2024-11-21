package com.example.demo.dao;

import java.util.List;


import com.example.demo.entity.Representante;


public interface RepresentanteDao {
	Representante create(Representante c);
	Representante update(Representante c);
	void delete(Long id);
	Representante read(Long id);
	List<Representante> readAll();
}
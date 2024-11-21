package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.Documentacion;


public interface DocumentacionDao {
	Documentacion create(Documentacion c);
	Documentacion update(Documentacion c);
	void delete(Long id);
	Documentacion read(Long id);
	List<Documentacion> readAll();
}
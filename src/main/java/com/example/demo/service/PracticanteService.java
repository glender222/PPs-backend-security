package com.example.demo.service;

import java.util.List;
 

import com.example.demo.entity.Practicante;


public interface PracticanteService {
	Practicante create(Practicante c);
	Practicante update(Practicante c);
	void delete(Long id);
	Practicante read(Long id);
	List<Practicante> readAll();
}
package com.example.demo.service;

import java.util.List;
 

import com.example.demo.entity.Directora;



public interface DirectoraService {
	Directora create(Directora c);
	Directora update(Directora c);
	void delete(Long id);
	Directora read(Long id);
	List<Directora> readAll();
}
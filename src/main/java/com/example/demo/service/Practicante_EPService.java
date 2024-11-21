package com.example.demo.service;

import java.util.List;
 

import com.example.demo.entity.Practicante_EP;


public interface Practicante_EPService {
	Practicante_EP create(Practicante_EP c);
	Practicante_EP update(Practicante_EP c);
	void delete(Long id);
	Practicante_EP read(Long id);
	List<Practicante_EP> readAll();
}
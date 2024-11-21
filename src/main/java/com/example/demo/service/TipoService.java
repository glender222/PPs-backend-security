package com.example.demo.service;

import java.util.List;
 

import com.example.demo.entity.Tipo;


public interface TipoService {
	Tipo create(Tipo c);
	Tipo update(Tipo c);
	void delete(Long id);
	Tipo read(Long id);
	List<Tipo> readAll();
}

package com.example.demo.dao;

import java.util.List;


import com.example.demo.entity.Practicante_EP;


public interface Practicante_EPDao {
	Practicante_EP create(Practicante_EP c);
	Practicante_EP update(Practicante_EP c);
	void delete(Long Practicante_EP);
	Practicante_EP read(Long id);
	List<Practicante_EP> readAll();
}
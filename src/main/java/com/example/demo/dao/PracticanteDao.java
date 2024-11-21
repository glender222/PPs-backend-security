package com.example.demo.dao;

import java.util.List;


import com.example.demo.entity.Practicante;


public interface PracticanteDao {
	Practicante create(Practicante c);
	Practicante update(Practicante c);
	void delete(Long id);
	Practicante read(Long id);
	List<Practicante> readAll();
}
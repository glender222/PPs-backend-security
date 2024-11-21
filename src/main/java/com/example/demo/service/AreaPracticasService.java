package com.example.demo.service;

import java.util.List;


import com.example.demo.entity.AreaPracticas;



public interface AreaPracticasService {
	AreaPracticas create(AreaPracticas c);
	AreaPracticas update(AreaPracticas c);
	void delete(Long id);
	AreaPracticas read(Long id);
	List<AreaPracticas> readAll();
}

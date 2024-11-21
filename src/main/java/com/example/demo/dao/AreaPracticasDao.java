package com.example.demo.dao;

import java.util.List;


import com.example.demo.entity.AreaPracticas;



public interface AreaPracticasDao {
	AreaPracticas create(AreaPracticas c);
	AreaPracticas update(AreaPracticas c);
	void delete(Long id);
	AreaPracticas read(Long id);
	List<AreaPracticas> readAll();
}

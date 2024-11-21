package com.example.demo.dao;
import java.util.List;


import com.example.demo.entity.Tutores;


public interface TutoresDao {
	Tutores create(Tutores c);
	Tutores update(Tutores c);
	void delete(Long id);
	Tutores read(Long id);
	List<Tutores> readAll();
}
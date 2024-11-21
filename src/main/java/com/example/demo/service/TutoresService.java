package com.example.demo.service;
import java.util.List;
 

import com.example.demo.entity.Tutores;


public interface TutoresService {
	Tutores create(Tutores c);
	Tutores update(Tutores c);
	void delete(Long id);
	Tutores read(Long id);
	List<Tutores> readAll();
}
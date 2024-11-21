package com.example.demo.daoImpl;


import java.util.List;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dao.LineaDao;
import com.example.demo.entity.Linea;
import com.example.demo.repository.LineaRepository;


@Component
public class LineaDaoImpl implements LineaDao {

	@Autowired
	private LineaRepository lineaRepository;
	
	@Override
	public Linea create(Linea c) {
		// TODO Auto-generated method stub
		return lineaRepository.save(c);
	}

	@Override
	public Linea update(Linea c) {
		// TODO Auto-generated method stub
		return lineaRepository.save(c);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		lineaRepository.deleteById(id);
	}

	@Override
	public Linea read(Long id) {
		// TODO Auto-generated method stub
		return lineaRepository.findById(id).get();
	}

	@Override
	public List<Linea> readAll() {
		// TODO Auto-generated method stub
		return lineaRepository.findAll();
	}

}


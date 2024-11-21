package com.example.demo.daoImpl;


import java.util.List;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dao.RubrosDao;
import com.example.demo.entity.Rubros;
import com.example.demo.repository.RubrosRepository;


@Component
public class RubrosDaoImpl implements RubrosDao {

	@Autowired
	private RubrosRepository rubrosRepository;
	
	@Override
	public Rubros create(Rubros c) {
		// TODO Auto-generated method stub
		return rubrosRepository.save(c);
	}

	@Override
	public Rubros update(Rubros c) {
		// TODO Auto-generated method stub
		return rubrosRepository.save(c);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		rubrosRepository.deleteById(id);
	}

	@Override
	public Rubros read(Long id) {
		// TODO Auto-generated method stub
		return rubrosRepository.findById(id).get();
	}

	@Override
	public List<Rubros> readAll() {
		// TODO Auto-generated method stub
		return rubrosRepository.findAll();
	}

}


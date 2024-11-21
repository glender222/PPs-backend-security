package com.example.demo.daoImpl;


import java.util.List;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dao.PersonaDao;
import com.example.demo.entity.Persona;
import com.example.demo.repository.PersonaRepository;


@Component
public class PersonaDaoImpl implements PersonaDao {

	@Autowired
	private PersonaRepository personaRepository;
	
	@Override
	public Persona create(Persona c) {
		// TODO Auto-generated method stub
		return personaRepository.save(c);
	}

	@Override
	public Persona update(Persona c) {
		// TODO Auto-generated method stub
		return personaRepository.save(c);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		personaRepository.deleteById(id);
	}

	@Override
	public Persona read(Long id) {
		// TODO Auto-generated method stub
		return personaRepository.findById(id).get();
	}

	@Override
	public List<Persona> readAll() {
		// TODO Auto-generated method stub
		return personaRepository.findAll();
	}

}


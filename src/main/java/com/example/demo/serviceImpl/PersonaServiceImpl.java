package com.example.demo.serviceImpl;

import java.util.List;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PersonaDao;
import com.example.demo.entity.Persona;
import com.example.demo.service.PersonaService;


@Service
public class PersonaServiceImpl implements PersonaService {

	@Autowired
	private PersonaDao dao;
	
	@Override
	public Persona create(Persona c) {
		// TODO Auto-generated method stub
		return dao.create(c);
	}

	@Override
	public Persona update(Persona c) {
		// TODO Auto-generated method stub
		return dao.update(c);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		dao.delete(id);
	}

	@Override
	public Persona read(Long id) {
		// TODO Auto-generated method stub
		return dao.read(id);
	}

	@Override
	public List<Persona> readAll() {
		// TODO Auto-generated method stub
		return dao.readAll();
	}

}

package com.example.demo.daoImpl;


import java.util.List;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dao.ProgramacionDao;
import com.example.demo.entity.Programacion;
import com.example.demo.repository.ProgramacionRepository;


@Component
public class ProgramacionDaoImpl implements ProgramacionDao {

	@Autowired
	private ProgramacionRepository programacionRepository;
	
	@Override
	public Programacion create(Programacion c) {
		// TODO Auto-generated method stub
		return programacionRepository.save(c);
	}

	@Override
	public Programacion update(Programacion c) {
		// TODO Auto-generated method stub
		return programacionRepository.save(c);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		programacionRepository.deleteById(id);
	}

	@Override
	public Programacion read(Long id) {
		// TODO Auto-generated method stub
		return programacionRepository.findById(id).get();
	}

	@Override
	public List<Programacion> readAll() {
		// TODO Auto-generated method stub
		return programacionRepository.findAll();
	}

}


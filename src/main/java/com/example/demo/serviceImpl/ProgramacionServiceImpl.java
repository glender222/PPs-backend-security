package com.example.demo.serviceImpl;

import java.util.List;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ProgramacionDao;
import com.example.demo.entity.Programacion;
import com.example.demo.service.ProgramacionService;


@Service
public class ProgramacionServiceImpl implements ProgramacionService {

	@Autowired
	private ProgramacionDao dao;
	
	@Override
	public Programacion create(Programacion c) {
		// TODO Auto-generated method stub
		return dao.create(c);
	}

	@Override
	public Programacion update(Programacion c) {
		// TODO Auto-generated method stub
		return dao.update(c);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		dao.delete(id);
	}

	@Override
	public Programacion read(Long id) {
		// TODO Auto-generated method stub
		return dao.read(id);
	}

	@Override
	public List<Programacion> readAll() {
		// TODO Auto-generated method stub
		return dao.readAll();
	}

}

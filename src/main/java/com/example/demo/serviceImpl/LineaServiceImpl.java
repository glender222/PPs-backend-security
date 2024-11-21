package com.example.demo.serviceImpl;

import java.util.List;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.LineaDao;
import com.example.demo.entity.Linea;
import com.example.demo.service.LineaService;


@Service
public class LineaServiceImpl implements LineaService {

	@Autowired
	private LineaDao dao;
	
	@Override
	public Linea create(Linea c) {
		// TODO Auto-generated method stub
		return dao.create(c);
	}

	@Override
	public Linea update(Linea c) {
		// TODO Auto-generated method stub
		return dao.update(c);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		dao.delete(id);
	}

	@Override
	public Linea read(Long id) {
		// TODO Auto-generated method stub
		return dao.read(id);
	}

	@Override
	public List<Linea> readAll() {
		// TODO Auto-generated method stub
		return dao.readAll();
	}

}

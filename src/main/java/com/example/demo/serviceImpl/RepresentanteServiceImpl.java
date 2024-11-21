package com.example.demo.serviceImpl;

import java.util.List;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.RepresentanteDao;
import com.example.demo.entity.Representante;
import com.example.demo.service.RepresentanteService;


@Service
public class RepresentanteServiceImpl implements RepresentanteService {

	@Autowired
	private RepresentanteDao dao;
	
	@Override
	public Representante create(Representante c) {
		// TODO Auto-generated method stub
		return dao.create(c);
	}

	@Override
	public Representante update(Representante c) {
		// TODO Auto-generated method stub
		return dao.update(c);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		dao.delete(id);
	}

	@Override
	public Representante read(Long id) {
		// TODO Auto-generated method stub
		return dao.read(id);
	}

	@Override
	public List<Representante> readAll() {
		// TODO Auto-generated method stub
		return dao.readAll();
	}

}

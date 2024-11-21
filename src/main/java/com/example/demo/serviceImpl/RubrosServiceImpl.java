package com.example.demo.serviceImpl;

import java.util.List;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.RubrosDao;
import com.example.demo.entity.Rubros;
import com.example.demo.service.RubrosService;


@Service
public class RubrosServiceImpl implements RubrosService {

	@Autowired
	private RubrosDao dao;
	
	@Override
	public Rubros create(Rubros c) {
		// TODO Auto-generated method stub
		return dao.create(c);
	}

	@Override
	public Rubros update(Rubros c) {
		// TODO Auto-generated method stub
		return dao.update(c);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		dao.delete(id);
	}

	@Override
	public Rubros read(Long id) {
		// TODO Auto-generated method stub
		return dao.read(id);
	}

	@Override
	public List<Rubros> readAll() {
		// TODO Auto-generated method stub
		return dao.readAll();
	}

}

package com.example.demo.serviceImpl;

import java.util.List;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PracticanteDao;
import com.example.demo.entity.Practicante;
import com.example.demo.service.PracticanteService;


@Service
public class PracticanteServiceImpl implements PracticanteService {

	@Autowired
	private PracticanteDao dao;
	
	@Override
	public Practicante create(Practicante c) {
		// TODO Auto-generated method stub
		return dao.create(c);
	}

	@Override
	public Practicante update(Practicante c) {
		// TODO Auto-generated method stub
		return dao.update(c);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		dao.delete(id);
	}

	@Override
	public Practicante read(Long id) {
		// TODO Auto-generated method stub
		return dao.read(id);
	}

	@Override
	public List<Practicante> readAll() {
		// TODO Auto-generated method stub
		return dao.readAll();
	}

}

package com.example.demo.serviceImpl;

import java.util.List;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.Practicante_EPDao;
import com.example.demo.entity.Practicante_EP;
import com.example.demo.service.Practicante_EPService;


@Service
public class Practicante_EPServiceImpl implements Practicante_EPService {

	@Autowired
	private Practicante_EPDao dao;
	
	@Override
	public Practicante_EP create(Practicante_EP c) {
		// TODO Auto-generated method stub
		return dao.create(c);
	}

	@Override
	public Practicante_EP update(Practicante_EP c) {
		// TODO Auto-generated method stub
		return dao.update(c);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		dao.delete(id);
	}

	@Override
	public Practicante_EP read(Long id) {
		// TODO Auto-generated method stub
		return dao.read(id);
	}

	@Override
	public List<Practicante_EP> readAll() {
		// TODO Auto-generated method stub
		return dao.readAll();
	}

}

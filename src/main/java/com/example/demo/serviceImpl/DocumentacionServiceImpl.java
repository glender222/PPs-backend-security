package com.example.demo.serviceImpl;

import java.util.List;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.DocumentacionDao;
import com.example.demo.entity.Documentacion;
import com.example.demo.service.DocumentacionService;


@Service
public class DocumentacionServiceImpl implements DocumentacionService {

	@Autowired
	private DocumentacionDao dao;
	
	@Override
	public Documentacion create(Documentacion c) {
		// TODO Auto-generated method stub
		return dao.create(c);
	}

	@Override
	public Documentacion update(Documentacion c) {
		// TODO Auto-generated method stub
		return dao.update(c);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		dao.delete(id);
	}

	@Override
	public Documentacion read(Long id) {
		// TODO Auto-generated method stub
		return dao.read(id);
	}

	@Override
	public List<Documentacion> readAll() {
		// TODO Auto-generated method stub
		return dao.readAll();
	}

}

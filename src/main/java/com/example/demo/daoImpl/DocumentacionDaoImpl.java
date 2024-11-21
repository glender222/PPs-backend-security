package com.example.demo.daoImpl;


import java.util.List;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dao.DocumentacionDao;
import com.example.demo.entity.Documentacion;
import com.example.demo.repository.DocumentacionRepository;


@Component
public class DocumentacionDaoImpl implements DocumentacionDao {

	@Autowired
	private DocumentacionRepository documentacionRepository;
	
	@Override
	public Documentacion create(Documentacion c) {
		// TODO Auto-generated method stub
		return documentacionRepository.save(c);
	}

	@Override
	public Documentacion update(Documentacion c) {
		// TODO Auto-generated method stub
		return documentacionRepository.save(c);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		documentacionRepository.deleteById(id);
	}

	@Override
	public Documentacion read(Long id) {
		// TODO Auto-generated method stub
		return documentacionRepository.findById(id).get();
	}

	@Override
	public List<Documentacion> readAll() {
		// TODO Auto-generated method stub
		return documentacionRepository.findAll();
	}

}


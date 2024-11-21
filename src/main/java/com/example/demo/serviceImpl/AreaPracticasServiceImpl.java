package com.example.demo.serviceImpl;

import java.util.List;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AreaPracticasDao;
import com.example.demo.entity.AreaPracticas;
import com.example.demo.service.AreaPracticasService;


@Service
public class AreaPracticasServiceImpl implements AreaPracticasService {

	@Autowired
	private AreaPracticasDao dao;
	
	@Override
	public AreaPracticas create(AreaPracticas c) {
		// TODO Auto-generated method stub
		return dao.create(c);
	}

	@Override
	public AreaPracticas update(AreaPracticas c) {
		// TODO Auto-generated method stub
		return dao.update(c);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		dao.delete(id);
	}

	@Override
	public AreaPracticas read(Long id) {
		// TODO Auto-generated method stub
		return dao.read(id);
	}

	@Override
	public List<AreaPracticas> readAll() {
		// TODO Auto-generated method stub
		return dao.readAll();
	}

}

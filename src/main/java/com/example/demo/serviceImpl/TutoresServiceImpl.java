package com.example.demo.serviceImpl;

import java.util.List;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.TutoresDao;
import com.example.demo.entity.Tutores;
import com.example.demo.service.TutoresService;


@Service
public class TutoresServiceImpl implements TutoresService {

	@Autowired
	private TutoresDao dao;
	
	@Override
	public Tutores create(Tutores c) {
		// TODO Auto-generated method stub
		return dao.create(c);
	}

	@Override
	public Tutores update(Tutores c) {
		// TODO Auto-generated method stub
		return dao.update(c);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		dao.delete(id);
	}

	@Override
	public Tutores read(Long id) {
		// TODO Auto-generated method stub
		return dao.read(id);
	}

	@Override
	public List<Tutores> readAll() {
		// TODO Auto-generated method stub
		return dao.readAll();
	}

}

package com.example.demo.serviceImpl;

import java.util.List;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PPPDao;
import com.example.demo.entity.PPP;
import com.example.demo.service.PPPService;


@Service
public class PPPServiceImpl implements PPPService {

	@Autowired
	private PPPDao dao;
	
	@Override
	public PPP create(PPP c) {
		// TODO Auto-generated method stub
		return dao.create(c);
	}

	@Override
	public PPP update(PPP c) {
		// TODO Auto-generated method stub
		return dao.update(c);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		dao.delete(id);
	}

	@Override
	public PPP read(Long id) {
		// TODO Auto-generated method stub
		return dao.read(id);
	}

	@Override
	public List<PPP> readAll() {
		// TODO Auto-generated method stub
		return dao.readAll();
	}

}

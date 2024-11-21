package com.example.demo.daoImpl;


import java.util.List;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dao.PPPDao;
import com.example.demo.entity.PPP;
import com.example.demo.repository.PPPRepository;


@Component
public class PPPDaoImpl implements PPPDao {

	@Autowired
	private PPPRepository pppRepository;
	
	@Override
	public PPP create(PPP c) {
		// TODO Auto-generated method stub
		return pppRepository.save(c);
	}

	@Override
	public PPP update(PPP c) {
		// TODO Auto-generated method stub
		return pppRepository.save(c);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		pppRepository.deleteById(id);
	}

	@Override
	public PPP read(Long id) {
		// TODO Auto-generated method stub
		return pppRepository.findById(id).get();
	}

	@Override
	public List<PPP> readAll() {
		// TODO Auto-generated method stub
		return pppRepository.findAll();
	}

}


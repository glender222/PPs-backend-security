package com.example.demo.daoImpl;


import java.util.List;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dao.Practicante_EPDao;
import com.example.demo.entity.Practicante_EP;
import com.example.demo.repository.Practicante_EPRepository;


@Component
public class Practicante_EPDaoImpl implements Practicante_EPDao {

	@Autowired
	private Practicante_EPRepository practicante_EPRepository;
	
	@Override
	public Practicante_EP create(Practicante_EP c) {
		// TODO Auto-generated method stub
		return practicante_EPRepository.save(c);
	}

	@Override
	public Practicante_EP update(Practicante_EP c) {
		// TODO Auto-generated method stub
		return practicante_EPRepository.save(c);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		practicante_EPRepository.deleteById(id);
	}

	@Override
	public Practicante_EP read(Long id) {
		// TODO Auto-generated method stub
		return practicante_EPRepository.findById(id).get();
	}

	@Override
	public List<Practicante_EP> readAll() {
		// TODO Auto-generated method stub
		return practicante_EPRepository.findAll();
	}

}


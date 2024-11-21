package com.example.demo.daoImpl;


import java.util.List;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dao.PracticanteDao;
import com.example.demo.entity.Practicante;
import com.example.demo.repository.PracticanteRepository;


@Component
public class PracticanteDaoImpl implements PracticanteDao {

	@Autowired
	private PracticanteRepository practicanteRepository;
	
	@Override
	public Practicante create(Practicante c) {
		// TODO Auto-generated method stub
		return practicanteRepository.save(c);
	}

	@Override
	public Practicante update(Practicante c) {
		// TODO Auto-generated method stub
		return practicanteRepository.save(c);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		practicanteRepository.deleteById(id);
	}

	@Override
	public Practicante read(Long id) {
		// TODO Auto-generated method stub
		return practicanteRepository.findById(id).get();
	}

	@Override
	public List<Practicante> readAll() {
		// TODO Auto-generated method stub
		return practicanteRepository.findAll();
	}

}


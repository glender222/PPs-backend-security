package com.example.demo.daoImpl;


import java.util.List;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dao.TutoresDao;
import com.example.demo.entity.Tutores;
import com.example.demo.repository.TutoresRepository;


@Component
public class TutoresDaoImpl implements TutoresDao {

	@Autowired
	private TutoresRepository tutoresRepository;
	
	@Override
	public Tutores create(Tutores c) {
		// TODO Auto-generated method stub
		return tutoresRepository.save(c);
	}

	@Override
	public Tutores update(Tutores c) {
		// TODO Auto-generated method stub
		return tutoresRepository.save(c);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		tutoresRepository.deleteById(id);
	}

	@Override
	public Tutores read(Long id) {
		// TODO Auto-generated method stub
		return tutoresRepository.findById(id).get();
	}

	@Override
	public List<Tutores> readAll() {
		// TODO Auto-generated method stub
		return tutoresRepository.findAll();
	}

}


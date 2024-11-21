package com.example.demo.daoImpl;


import java.util.List;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dao.RepresentanteDao;
import com.example.demo.entity.Representante;
import com.example.demo.repository.RepresentanteRepository;


@Component
public class RepresentanteDaoImpl implements RepresentanteDao {

	@Autowired
	private RepresentanteRepository representanteRepository;
	
	@Override
	public Representante create(Representante c) {
		// TODO Auto-generated method stub
		return representanteRepository.save(c);
	}

	@Override
	public Representante update(Representante c) {
		// TODO Auto-generated method stub
		return representanteRepository.save(c);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		representanteRepository.deleteById(id);
	}

	@Override
	public Representante read(Long id) {
		// TODO Auto-generated method stub
		return representanteRepository.findById(id).get();
	}

	@Override
	public List<Representante> readAll() {
		// TODO Auto-generated method stub
		return representanteRepository.findAll();
	}

}


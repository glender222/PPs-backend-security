package com.example.demo.daoImpl;


import java.util.List;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dao.TipoDao;
import com.example.demo.entity.Tipo;
import com.example.demo.repository.TipoRepository;


@Component
public class TipoDaoImpl implements TipoDao {

	@Autowired
	private TipoRepository evidenciaRepository;
	
	@Override
	public Tipo create(Tipo c) {
		// TODO Auto-generated method stub
		return evidenciaRepository.save(c);
	}

	@Override
	public Tipo update(Tipo c) {
		// TODO Auto-generated method stub
		return evidenciaRepository.save(c);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		evidenciaRepository.deleteById(id);
	}

	@Override
	public Tipo read(Long id) {
		// TODO Auto-generated method stub
		return evidenciaRepository.findById(id).get();
	}

	@Override
	public List<Tipo> readAll() {
		// TODO Auto-generated method stub
		return evidenciaRepository.findAll();
	}

}


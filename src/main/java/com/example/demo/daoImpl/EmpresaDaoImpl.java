package com.example.demo.daoImpl;


import java.util.List;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dao.EmpresaDao;
import com.example.demo.entity.Empresa;
import com.example.demo.repository.EmpresaRepository;


@Component
public class EmpresaDaoImpl implements EmpresaDao {

	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Override
	public Empresa create(Empresa c) {
		// TODO Auto-generated method stub
		return empresaRepository.save(c);
	}

	@Override
	public Empresa update(Empresa c) {
		// TODO Auto-generated method stub
		return empresaRepository.save(c);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		empresaRepository.deleteById(id);
	}

	@Override
	public Empresa read(Long id) {
		// TODO Auto-generated method stub
		return empresaRepository.findById(id).get();
	}

	@Override
	public List<Empresa> readAll() {
		// TODO Auto-generated method stub
		return empresaRepository.findAll();
	}

}

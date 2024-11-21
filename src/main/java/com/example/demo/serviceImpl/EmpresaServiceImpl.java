package com.example.demo.serviceImpl;

import java.util.List;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.EmpresaDao;
import com.example.demo.entity.Empresa;
import com.example.demo.service.EmpresaService;


@Service
public class EmpresaServiceImpl implements EmpresaService {

	@Autowired
	private EmpresaDao dao;
	
	@Override
	public Empresa create(Empresa c) {
		// TODO Auto-generated method stub
		return dao.create(c);
	}

	@Override
	public Empresa update(Empresa c) {
		// TODO Auto-generated method stub
		return dao.update(c);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		dao.delete(id);
	}

	@Override
	public Empresa read(Long id) {
		// TODO Auto-generated method stub
		return dao.read(id);
	}

	@Override
	public List<Empresa> readAll() {
		// TODO Auto-generated method stub
		return dao.readAll();
	}

}

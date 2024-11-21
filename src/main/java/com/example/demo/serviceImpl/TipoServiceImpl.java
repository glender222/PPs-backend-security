package com.example.demo.serviceImpl;

import java.util.List;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.TipoDao;
import com.example.demo.entity.Tipo;
import com.example.demo.service.TipoService;


@Service
public class TipoServiceImpl implements TipoService {

	@Autowired
	private TipoDao dao;
	
	@Override
	public Tipo create(Tipo c) {
		// TODO Auto-generated method stub
		return dao.create(c);
	}

	@Override
	public Tipo update(Tipo c) {
		// TODO Auto-generated method stub
		return dao.update(c);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		dao.delete(id);
	}

	@Override
	public Tipo read(Long id) {
		// TODO Auto-generated method stub
		return dao.read(id);
	}

	@Override
	public List<Tipo> readAll() {
		// TODO Auto-generated method stub
		return dao.readAll();
	}

}

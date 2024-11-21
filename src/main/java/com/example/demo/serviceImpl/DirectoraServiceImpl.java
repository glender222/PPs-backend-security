package com.example.demo.serviceImpl;

import java.util.List;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.DirectoraDao;
import com.example.demo.entity.Directora;
import com.example.demo.service.DirectoraService;


@Service
public class DirectoraServiceImpl implements DirectoraService {

	@Autowired
	private DirectoraDao dao;
	
	@Override
	public Directora create(Directora c) {
		// TODO Auto-generated method stub
		return dao.create(c);
	}

	@Override
	public Directora update(Directora c) {
		// TODO Auto-generated method stub
		return dao.update(c);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		dao.delete(id);
	}

	@Override
	public Directora read(Long id) {
		// TODO Auto-generated method stub
		return dao.read(id);
	}

	@Override
	public List<Directora> readAll() {
		// TODO Auto-generated method stub
		return dao.readAll();
	}

}

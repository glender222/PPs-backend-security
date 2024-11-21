package com.example.demo.daoImpl;


import java.util.List;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dao.DirectoraDao;
import com.example.demo.entity.Directora;
import com.example.demo.repository.DirectoraRepository;


@Component
public class DirectoraDaoImpl implements DirectoraDao {

	@Autowired
	private DirectoraRepository directoraRepository;
	
	@Override
	public Directora create(Directora c) {
		// TODO Auto-generated method stub
		return directoraRepository.save(c);
	}

	@Override
	public Directora update(Directora c) {
		// TODO Auto-generated method stub
		return directoraRepository.save(c);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		directoraRepository.deleteById(id);
	}

	@Override
	public Directora read(Long id) {
		// TODO Auto-generated method stub
		return directoraRepository.findById(id).get();
	}

	@Override
	public List<Directora> readAll() {
		// TODO Auto-generated method stub
		return directoraRepository.findAll();
	}

}

package com.example.demo.daoImpl;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dao.AreaPracticasDao;
import com.example.demo.entity.AreaPracticas;
import com.example.demo.repository.AreaPracticasRepository;


@Component
public class AreaPracticasDaoImpl implements AreaPracticasDao {

	@Autowired
	private AreaPracticasRepository areapracticasRepository;
	
	@Override
	public AreaPracticas create(AreaPracticas c) {
		// TODO Auto-generated method stub
		return areapracticasRepository.save(c);
	}

	@Override
	public AreaPracticas update(AreaPracticas c) {
		// TODO Auto-generated method stub
		return areapracticasRepository.save(c);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		areapracticasRepository.deleteById(id);
	}

	@Override
	public AreaPracticas read(Long id) {
		// TODO Auto-generated method stub
		return areapracticasRepository.findById(id).get();
	}

	@Override
	public List<AreaPracticas> readAll() {
		// TODO Auto-generated method stub
		return areapracticasRepository.findAll();
	}

}

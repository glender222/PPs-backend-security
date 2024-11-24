package com.example.demo.serviceImpl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Documentacion;
import com.example.demo.repository.DocumentacionRepository;
import com.example.demo.service.DocumentacionService;



@Service
public class DocumentacionServiceImpl  implements DocumentacionService {

@Autowired
	private DocumentacionRepository documentacionRepository;
	
	@Override
	public Documentacion create(Documentacion c) {
	    return documentacionRepository.save(c); 
	}

	@Override
	public Documentacion update(Documentacion c) {
	    return documentacionRepository.save(c); 
	}

	@Override
	public void delete(Long id) {
	    documentacionRepository.deleteById(id);
	}

	@Override
	public Documentacion read(Long id) {
	    return documentacionRepository.findById(id).orElse(null);
	}

	@Override
	public List<Documentacion> readAll() {
	    return documentacionRepository.findAll();
}
}
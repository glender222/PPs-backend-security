package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Practicante_EP;
import com.example.demo.repository.Practicante_EPRepository;
import com.example.demo.service.Practicante_EPService;
@Service
public class Practicante_EPServiceImpl implements Practicante_EPService{
	@Autowired
		private Practicante_EPRepository practicante_EPRepository;
		
		@Override
		public Practicante_EP create(Practicante_EP c) {
		    return practicante_EPRepository.save(c); 
		}
	
		@Override
		public Practicante_EP update(Practicante_EP c) {
		    return practicante_EPRepository.save(c); 
		}
	
		@Override
		public void delete(Long id) {
			practicante_EPRepository.deleteById(id);
		}
	
		@Override
		public Practicante_EP read(Long id) {
		    return practicante_EPRepository.findById(id).orElse(null);
		}
	
		@Override
		public List<Practicante_EP> readAll() {
		    return practicante_EPRepository.findAll();
		}

}

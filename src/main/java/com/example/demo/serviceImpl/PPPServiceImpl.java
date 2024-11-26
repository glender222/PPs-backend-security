package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.PPP;
import com.example.demo.repository.PPPRepository;
import com.example.demo.service.PPPService;


@Service
public class PPPServiceImpl implements PPPService{

		@Autowired
		private PPPRepository pppRepository;
		
		@Override
		public PPP create(PPP c) {
		    return pppRepository.save(c); 
		}
	
		@Override
		public PPP update(PPP c) {
		    return pppRepository.save(c); 
		}
	
		@Override
		public void delete(Long id) {
			pppRepository.deleteById(id);
		}
	
		@Override
		public PPP read(Long id) {
		    return pppRepository.findById(id).orElse(null);
		}
	
		@Override
		public List<PPP> readAll() {
		    return pppRepository.findAll();
		}



}

package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.TipoDocumento;
import com.example.demo.repository.TipoDocumentoRepository;
import com.example.demo.service.TipoDocumentoService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class TipoDocumentoServiceImpl implements TipoDocumentoService {
    
	private final TipoDocumentoRepository tipodocumentacionRepository;
	
	@Override
	public TipoDocumento create(TipoDocumento c) {
	    return tipodocumentacionRepository.save(c); 
	}

	@Override
	public TipoDocumento update(TipoDocumento c) {
	    return tipodocumentacionRepository.save(c); 
	}

	@Override
	public void delete(Long id) {
	    tipodocumentacionRepository.deleteById(id);
	}

	@Override
	public TipoDocumento read(Long id) {
	    return tipodocumentacionRepository.findById(id).orElse(null);
	}

	@Override
	public List<TipoDocumento> readAll() {
	    return tipodocumentacionRepository.findAll();
	}

}

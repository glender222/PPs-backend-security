package com.example.demo.daoImpl;


import java.util.List;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dao.TipoDocumentoDao;
import com.example.demo.entity.TipoDocumento;
import com.example.demo.repository.TipoDocumentoRepository;


@Component
public class TipoDocumentoDaoImpl implements TipoDocumentoDao {

	@Autowired
	private TipoDocumentoRepository tipodocumentoRepository;
	
	@Override
	public TipoDocumento create(TipoDocumento c) {
		// TODO Auto-generated method stub
		return tipodocumentoRepository.save(c);
	}

	@Override
	public TipoDocumento update(TipoDocumento c) {
		// TODO Auto-generated method stub
		return tipodocumentoRepository.save(c);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		tipodocumentoRepository.deleteById(id);
	}

	@Override
	public TipoDocumento read(Long id) {
		// TODO Auto-generated method stub
		return tipodocumentoRepository.findById(id).get();
	}

	@Override
	public List<TipoDocumento> readAll() {
		// TODO Auto-generated method stub
		return tipodocumentoRepository.findAll();
	}

}


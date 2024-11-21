package com.example.demo.serviceImpl;

import java.util.List;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.TipoDocumentoDao;
import com.example.demo.entity.TipoDocumento;
import com.example.demo.service.TipoDocumentoService;


@Service
public class TipoDocumentoServiceImpl implements TipoDocumentoService {

	@Autowired
	private TipoDocumentoDao dao;
	
	@Override
	public TipoDocumento create(TipoDocumento c) {
		// TODO Auto-generated method stub
		return dao.create(c);
	}

	@Override
	public TipoDocumento update(TipoDocumento c) {
		// TODO Auto-generated method stub
		return dao.update(c);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		dao.delete(id);
	}

	@Override
	public TipoDocumento read(Long id) {
		// TODO Auto-generated method stub
		return dao.read(id);
	}

	@Override
	public List<TipoDocumento> readAll() {
		// TODO Auto-generated method stub
		return dao.readAll();
	}

}

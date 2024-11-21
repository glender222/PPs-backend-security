package com.example.demo.service;

import java.util.List;
 

import com.example.demo.entity.TipoDocumento;


public interface TipoDocumentoService {
	TipoDocumento create(TipoDocumento c);
	TipoDocumento update(TipoDocumento c);
	void delete(Long id);
	TipoDocumento read(Long id);
	List<TipoDocumento> readAll();
}
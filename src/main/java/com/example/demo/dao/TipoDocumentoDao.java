package com.example.demo.dao;

import java.util.List;


import com.example.demo.entity.TipoDocumento;


public interface TipoDocumentoDao {
	TipoDocumento create(TipoDocumento c);
	TipoDocumento update(TipoDocumento c);
	void delete(Long id);
	TipoDocumento read(Long id);
	List<TipoDocumento> readAll();
}
package com.example.demo.dao;

import java.util.List;


import com.example.demo.entity.Tipo;


public interface TipoDao {
	Tipo create(Tipo c);
	Tipo update(Tipo c);
	void delete(Long id);
	Tipo read(Long id);
	List<Tipo> readAll();
}

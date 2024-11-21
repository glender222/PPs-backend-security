package com.example.demo.dao;

import java.util.List;


import com.example.demo.entity.Empresa;



public interface EmpresaDao {
	Empresa create(Empresa c);
	Empresa update(Empresa c);
	void delete(Long id);
	Empresa read(Long id);
	List<Empresa> readAll();
}

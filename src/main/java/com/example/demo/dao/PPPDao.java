package com.example.demo.dao;

import java.util.List;


import com.example.demo.entity.PPP;


public interface PPPDao {
	PPP create(PPP c);
	PPP update(PPP c);
	void delete(Long id);
	PPP read(Long id);
	List<PPP> readAll();
}
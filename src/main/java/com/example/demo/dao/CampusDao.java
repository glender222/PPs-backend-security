package com.example.demo.dao;

import java.util.List;


import com.example.demo.entity.Campus;



public interface CampusDao {
	Campus create(Campus c);
	Campus update(Campus c);
	void delete(Long id);
	Campus read(Long id);
	List<Campus> readAll();
}

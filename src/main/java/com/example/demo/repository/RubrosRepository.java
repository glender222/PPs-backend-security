package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Rubros;

@Repository
public interface RubrosRepository extends JpaRepository<Rubros, Long>{

}
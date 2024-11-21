package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Tipo;

@Repository
public interface TipoRepository extends JpaRepository<Tipo, Long>{

}

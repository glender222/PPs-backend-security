package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Directora;

@Repository
public interface DirectoraRepository extends JpaRepository<Directora, Long>{

}

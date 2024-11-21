package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Tutores;

@Repository
public interface TutoresRepository extends JpaRepository<Tutores, Long>{

}
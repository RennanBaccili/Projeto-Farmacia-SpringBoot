package com.treino.java.remedio.rennan.remedio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.treino.java.remedio.rennan.remedio.Remedio;

public interface RemedioRepository extends JpaRepository<Remedio, Long>{

	List<Remedio> findAllByAtivoTrue();

}

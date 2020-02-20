package com.at.model.persistence;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.at.model.Tablero;

@Repository
public interface TableroRepository extends JpaRepository<Tablero, Integer>{

	public void deleteById(int id);
	
	public List<Tablero> findByNombre(String nombre);
	

}

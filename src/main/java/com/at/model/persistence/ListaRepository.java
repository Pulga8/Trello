package com.at.model.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.at.model.Lista;

@Repository
public interface ListaRepository extends JpaRepository<Lista, Long>{

	
		//public void refresh(int id_tablero,int posicion);
		
}

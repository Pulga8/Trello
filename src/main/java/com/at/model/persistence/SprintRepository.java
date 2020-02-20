package com.at.model.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.at.model.Sprint;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Integer>{
			
		//	public List<Sprint>findByNombre(String Nombre);
			
		//	public List<Sprint> findByTablero_Nombre(int id_tablero, String nombre);
			
			
}

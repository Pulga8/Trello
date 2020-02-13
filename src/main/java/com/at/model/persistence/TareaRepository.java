package com.at.model.persistence;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.at.model.Tarea;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long>{
	
	//List<Tarea> findByLista_IdOrderByPosicionAsc(int id);

}

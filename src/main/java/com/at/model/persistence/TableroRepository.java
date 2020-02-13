package com.at.model.persistence;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.at.model.Tablero;

@Repository
public interface TableroRepository extends JpaRepository<Tablero, Long>{

	public void deleteById(long id);
	

}

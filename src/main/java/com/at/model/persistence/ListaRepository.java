package com.at.model.persistence;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.at.model.Lista;

@Repository
public interface ListaRepository extends JpaRepository<Lista, Integer>{

	
	public List<Lista> findByTablero_Id(int id);
	
	@Modifying
    @Transactional
    @Query(value = "UPDATE listas SET posicion = posicion - 1 WHERE id_tablero = ? AND posicion >= ?;", nativeQuery = true)
	public List<Lista> refreshOnDelete(int id_tablero, int posicion);
	
	
	
	@Modifying
    @Transactional
    @Query(value = "UPDATE listas SET posicion = posicion + 1 WHERE id_tablero = ? AND posicion >= ?;", nativeQuery = true)
    public void refreshOnSave(int id_tablero, int posicion);

		
}

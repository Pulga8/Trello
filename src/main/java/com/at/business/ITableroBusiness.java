package com.at.business;

import java.util.List;

import com.at.model.Tablero;

public interface ITableroBusiness {
	public Tablero load(int id) throws BusinessException, NotFoundException;

	public Tablero add(Tablero tablero) throws BusinessException;

	public void delete(int id) throws BusinessException;

	public Tablero update(Tablero tablero) throws BusinessException;

	public List<Tablero> list() throws BusinessException;
}

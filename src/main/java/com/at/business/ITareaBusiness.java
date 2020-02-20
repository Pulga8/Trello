package com.at.business;

import java.util.List;

//import com.at.model.Lista;
import com.at.model.Tarea;

public interface ITareaBusiness {

	public Tarea load(int id) throws BusinessException, NotFoundException;

	public Tarea add(Tarea tarea) throws BusinessException;

	public void delete(int id) throws BusinessException;

	public Tarea update(Tarea tarea) throws BusinessException;

	public List<Tarea> list() throws BusinessException;

//	public List<Tarea> list(Lista lista) throws BusinessException;
}

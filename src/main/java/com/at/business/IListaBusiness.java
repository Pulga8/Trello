package com.at.business;

import java.util.List;

import com.at.model.Lista;

public interface IListaBusiness {

	public Lista load(long id) throws BusinessException, NotFoundException;

	public Lista add(Lista lista) throws BusinessException;

	public void delete(long id) throws BusinessException;

	public Lista update(Lista lista) throws BusinessException;

	public List<Lista> list() throws BusinessException;
}

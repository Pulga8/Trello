package com.at.business;

import java.util.List;

import com.at.model.Sprint;

public interface ISprintBusiness {

	
	public Sprint load(long id) throws BusinessException, NotFoundException;

	public Sprint add(Sprint sprint) throws BusinessException;

	public void delete(long id) throws BusinessException;

	public Sprint update(Sprint sprint) throws BusinessException;

	public List<Sprint> list() throws BusinessException;
}

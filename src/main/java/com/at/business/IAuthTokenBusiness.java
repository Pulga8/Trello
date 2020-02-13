package com.at.business;

import com.at.business.BusinessException;
import com.at.business.NotFoundException;
import com.at.model.AuthToken;

public interface IAuthTokenBusiness {
	public AuthToken save(AuthToken at) throws BusinessException;

	public AuthToken load(String series) throws BusinessException, NotFoundException;

	public void delete(AuthToken at) throws BusinessException;

}
package com.at;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.at.business.BusinessException;
import com.at.business.IUsuarioBusiness;
import com.at.business.NotFoundException;

@Service
public class PersistenceUserDetailsService implements UserDetailsService {

	@Autowired
	private IUsuarioBusiness usuarioBusiness;
	
	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		try {
			return (UserDetails) usuarioBusiness.load(username);
		} catch (BusinessException e) {
			throw new RuntimeException(e.getMessage(),e);
		} catch (NotFoundException e) {
			throw new UsernameNotFoundException(e.getMessage(),e);
		}
		
	}

}
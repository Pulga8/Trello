package com.at.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.at.model.Tablero;

import com.at.model.persistence.TableroRepository;

@Service
public class TableroBusiness implements ITableroBusiness {

	
	@Autowired
	private TableroRepository tableroDAO;
	
	@Override
	public Tablero load(int id) throws BusinessException, NotFoundException {
		Optional<Tablero> o;
		try {
			o = tableroDAO.findById(id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		if (o.isPresent())
			return o.get();
		else
			throw new NotFoundException();
	}

	@Override
	public Tablero add(Tablero tablero) throws BusinessException {
		try {
			return tableroDAO.save(tablero);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public void delete(int id) throws BusinessException {
		try {
			tableroDAO.deleteById(id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		
	}

	@Override
	public Tablero update(Tablero tablero) throws BusinessException {
		try {
			return tableroDAO.save(tablero);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public List<Tablero> list() throws BusinessException {
		try {
			return tableroDAO.findAll();
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

}

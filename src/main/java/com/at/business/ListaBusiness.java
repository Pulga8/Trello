package com.at.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.at.model.Lista;
import com.at.model.persistence.ListaRepository;

@Service
public class ListaBusiness implements IListaBusiness {

	@Autowired
	private ListaRepository listaDAO;
	
	

	@Override
	public Lista load(int id) throws BusinessException, NotFoundException {
		Optional<Lista> o;
		try {
			o = listaDAO.findById(id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		if (o.isPresent())
			return o.get();
		else
			throw new NotFoundException();
	}

	@Override
	public Lista add(Lista lista) throws BusinessException {

		try {
			listaDAO.refreshOnSave(lista.getTablero().getId(), lista.getPosicion());
			return listaDAO.save(lista);
			
		} catch (Exception e) {
			throw new BusinessException(e);
		}

	}

	@Override
	public void delete(int id) throws BusinessException {
		try {
				Lista l = listaDAO.findById(id).get();
				listaDAO.deleteById(id);
				listaDAO.refreshOnDelete(l.getTablero().getId(), l.getPosicion());
				
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public Lista update(Lista lista) throws BusinessException {

		try {
			return listaDAO.save(lista);
		} catch (Exception e) {
			throw new BusinessException(e);
		}

	}

	@Override
	public List<Lista> list() throws BusinessException {
		try {
			return listaDAO.findAll();
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}


}

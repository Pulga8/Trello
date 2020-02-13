package com.at.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.at.model.Tarea;
import com.at.model.persistence.TareaRepository;

@Service
public class TareaBusiness implements ITareaBusiness {

		@Autowired
		private TareaRepository tareaDAO;

		@Override
		public Tarea load(long id) throws BusinessException, NotFoundException {
			Optional<Tarea> o;
			try {
				o = tareaDAO.findById(id);
			} catch (Exception e) {
				throw new BusinessException(e);
			}
			if (o.isPresent())
				return o.get();
			else
				throw new NotFoundException();
		}

		@Override
		public Tarea add(Tarea tarea) throws BusinessException {

			try {
				return tareaDAO.save(tarea);
			} catch (Exception e) {
				throw new BusinessException(e);
			}

		}

		@Override
		public void delete(long id) throws BusinessException {
			try {
				tareaDAO.deleteById(id);
			} catch (Exception e) {
				throw new BusinessException(e);
			}
		}

		@Override
		public Tarea update(Tarea tarea) throws BusinessException {

			try {
				return tareaDAO.save(tarea);
			} catch (Exception e) {
				throw new BusinessException(e);
			}

		}

		@Override
		public List<Tarea> list() throws BusinessException {
			try {
				return tareaDAO.findAll();
			} catch (Exception e) {
				throw new BusinessException(e);
			}
		}


//		@Override
//		public List<Tarea> list(Lista lista) throws BusinessException {
//			try {
//				if (lista != null) {
//					return tareaDAO.findByListaAndOrderByPrioridadOrFechaDeCreacion(lista);
//				} else {
//					throw new BusinessException("Parámetros incorrectos");
//				}
//			} catch (Exception e) {
//				throw new BusinessException(e);
//			}
//		}

	}


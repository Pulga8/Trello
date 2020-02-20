package com.at.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import com.at.business.BusinessException;
import com.at.business.ITareaBusiness;
import com.at.business.NotFoundException;
import com.at.model.Tarea;



public class TareasRestService {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ITareaBusiness tareaBusiness;

	@GetMapping(Constantes.URL_TAREA + "/{id}")
	public ResponseEntity<Tarea> loadTarea(@PathVariable("id") int id) {
		try {
			return new ResponseEntity<Tarea>(tareaBusiness.load(id), HttpStatus.OK);
		} catch (BusinessException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(), e);
		} catch (NotFoundException e) {
			return new ResponseEntity<Tarea>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(Constantes.URL_TAREA)
	public ResponseEntity<Tarea> addTarea(@RequestBody Tarea tarea) {
		try {
			return new ResponseEntity<Tarea>(tareaBusiness.add(tarea), HttpStatus.CREATED);
		} catch (BusinessException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(), e);
		}
	}

	@PutMapping(Constantes.URL_TAREA)
	public ResponseEntity<Tarea> updateTarea(@RequestBody Tarea tarea) {
		try {
			return new ResponseEntity<Tarea>(tareaBusiness.update(tarea), HttpStatus.OK);
		} catch (BusinessException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(), e);
		}
	}

	@DeleteMapping(Constantes.URL_TAREA + "/{id}")
	public ResponseEntity<String> deleteTarea(@PathVariable("id") int id) {
		try {
			tareaBusiness.delete(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			log.error(e.getMessage(),e);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(), e);
		}
	}
}

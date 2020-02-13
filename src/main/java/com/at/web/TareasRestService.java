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

import com.at.business.BusinessException;
import com.at.business.ITareaBusiness;
import com.at.business.NotFoundException;
import com.at.model.Tarea;


public class TareasRestService {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ITareaBusiness tareaBusiness;

	@GetMapping("/{id}")
	public ResponseEntity<Tarea> load(@PathVariable("id") long id) {
		try {
			return new ResponseEntity<Tarea>(tareaBusiness.load(id), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<Tarea>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Tarea>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("")
	public ResponseEntity<Tarea> add(@RequestBody Tarea tarea) {
		try {
			return new ResponseEntity<Tarea>(tareaBusiness.add(tarea), HttpStatus.CREATED);
		} catch (BusinessException e) {
			return new ResponseEntity<Tarea>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("")
	public ResponseEntity<Tarea> update(@RequestBody Tarea tarea) {
		try {
			return new ResponseEntity<Tarea>(tareaBusiness.update(tarea), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<Tarea>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") long id) {
		try {
			tareaBusiness.delete(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			log.error(e.getMessage(),e);
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

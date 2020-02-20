package com.at.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.at.business.BusinessException;
import com.at.business.IListaBusiness;
import com.at.business.NotFoundException;
import com.at.model.Lista;

@RestController
@RequestMapping(Constantes.URL_LISTA)
public class ListaRestService extends BaseRestService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IListaBusiness listaBusiness;

	@GetMapping(Constantes.URL_LISTA + "/{id}")
	public ResponseEntity<Lista> loadLista(@PathVariable("id") int id) {
		try {
			return new ResponseEntity<Lista>(listaBusiness.load(id), HttpStatus.OK);
		} catch (BusinessException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(), e);
		} catch (NotFoundException e) {
			return new ResponseEntity<Lista>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(Constantes.URL_LISTA)
	public ResponseEntity<Lista> addLista(@RequestBody Lista lista) {
		try {
			listaBusiness.add(lista);

			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", "/lista/" + lista.getId());
			return new ResponseEntity<Lista>(responseHeaders, HttpStatus.CREATED);
		} catch (BusinessException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(), e);
		}
	}

	@PutMapping(Constantes.URL_LISTA)
	public ResponseEntity<Lista> updateLista(@RequestBody Lista lista) {
		try {
			return new ResponseEntity<Lista>(listaBusiness.update(lista), HttpStatus.OK);
		} catch (BusinessException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(), e);
		}
	}

	@DeleteMapping(Constantes.URL_LISTA + "/{id}")
	public ResponseEntity<String> deleteLista(@PathVariable("id") int id) {
		try {
			listaBusiness.delete(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(), e);
		}
	}

	@GetMapping(Constantes.URL_LIST_LISTAS)
	public ResponseEntity<List<Lista>> listListas(@PathVariable("idTablero") int idTablero) {
		try {
			return new ResponseEntity<List<Lista>>(listaBusiness.list(), HttpStatus.OK);
		} catch (BusinessException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(), e);
		}
	}

}

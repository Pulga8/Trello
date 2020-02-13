package com.at.web;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.at.business.BusinessException;
import com.at.business.IListaBusiness;
import com.at.business.NotFoundException;
import com.at.model.Lista;


@RestController
@RequestMapping(Constantes.URL_LISTA)
public class ListaRestService extends BaseRestService{


	@Autowired
	private IListaBusiness listaBusiness;

	@GetMapping("")
	public ResponseEntity<List<Lista>> list(
			@RequestParam(required = false, defaultValue = "@*@", value = "q") String parteDelNombre) {
		try {
			if (parteDelNombre.equals("@*@")) {
				return new ResponseEntity<List<Lista>>(listaBusiness.list(), HttpStatus.OK);
			} else {
				return new ResponseEntity<List<Lista>>(listaBusiness.list(), HttpStatus.OK);
			}
		} catch (BusinessException e) {
			return new ResponseEntity<List<Lista>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Lista> load(@PathVariable("id") long id) {
		try {
			return new ResponseEntity<Lista>(listaBusiness.load(id), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<Lista>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Lista>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("")
	public ResponseEntity<Lista> add(@RequestBody Lista lista) {
		try {
			listaBusiness.add(lista);
			
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", "/lista/" + lista.getId());
			return new ResponseEntity<Lista>(responseHeaders, HttpStatus.CREATED);
		} catch (BusinessException e) {
			return new ResponseEntity<Lista>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("")
	public ResponseEntity<Lista> update(@RequestBody Lista lista) {
		try {
			return new ResponseEntity<Lista>(listaBusiness.update(lista), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<Lista>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") long id) {
		try {
			listaBusiness.delete(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}






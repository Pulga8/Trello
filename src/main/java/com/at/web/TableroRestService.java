package com.at.web;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.at.business.BusinessException;
import com.at.business.ITableroBusiness;
import com.at.business.NotFoundException;
import com.at.model.Tablero;

@RestController
public class TableroRestService {

	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	  @Autowired
	    private ITableroBusiness tableroBusiness;

	
    @GetMapping(Constantes.URL_TABLEROS)
    public ResponseEntity<List<Tablero>> list() {
        try {
            return new ResponseEntity<List<Tablero>>(tableroBusiness.list(), HttpStatus.OK);
        } catch (BusinessException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(), e);
        }
    }

    @GetMapping(Constantes.URL_TABLEROS + "/{id}")
    public ResponseEntity<Tablero> loadTablero(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<Tablero>(tableroBusiness.load(id), HttpStatus.OK);
        } catch (BusinessException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(), e);
        } catch (NotFoundException e) {
            return new ResponseEntity<Tablero>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(Constantes.URL_TABLEROS)
    public ResponseEntity<Tablero> addTablero(@Valid @RequestBody Tablero tablero) {
        try {
            return new ResponseEntity<Tablero>(tableroBusiness.add(tablero), HttpStatus.CREATED);
        } catch (BusinessException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(), e);
        }
    }

    @PutMapping(Constantes.URL_TABLEROS)
    public ResponseEntity<Tablero> updateTablero(@Valid @RequestBody Tablero tablero) {
        try {
            return new ResponseEntity<Tablero>(tableroBusiness.update(tablero), HttpStatus.OK);
        } catch (BusinessException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(), e);
        }
    }

    @DeleteMapping(Constantes.URL_TABLEROS + "/{id}")
    public ResponseEntity<String> deleteTablero(@PathVariable("id") int id) {
        try {
            tableroBusiness.delete(id);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(), e);
        }
    }

}

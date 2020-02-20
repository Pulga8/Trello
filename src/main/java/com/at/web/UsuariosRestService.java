package com.at.web;

import java.util.ArrayList;
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
import org.springframework.web.server.ResponseStatusException;

import com.at.business.BusinessException;
import com.at.business.IUsuarioBusiness;
import com.at.business.NotFoundException;
import com.at.model.Usuario;

@RestController
@RequestMapping(Constantes.URL_USUARIOS)
public class UsuariosRestService extends BaseRestService {
	@Autowired
	private IUsuarioBusiness usuarioBusiness;

	@GetMapping("")
	public ResponseEntity<List<Usuario>> list(
			@RequestParam(required = false, defaultValue = "@*@", value = "q") String parteDelNombre,
			@RequestParam(required = false, defaultValue = "@*@", value = "us") String usernameOrEmail) {
		try {
			if (parteDelNombre.equals("@*@") && usernameOrEmail.equals("@*@")) {
				return new ResponseEntity<List<Usuario>>(usuarioBusiness.list(), HttpStatus.OK);
			} else if (parteDelNombre.equals("@*@")) {
				List<Usuario> r = new ArrayList<>();
				r.add(usuarioBusiness.load(usernameOrEmail));
				return new ResponseEntity<List<Usuario>>(r, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<Usuario>>(usuarioBusiness.list(parteDelNombre), HttpStatus.OK);
			}
		} catch (BusinessException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(), e);
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage(), e);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> load(@PathVariable("id") long id) {
		try {
			return new ResponseEntity<Usuario>(usuarioBusiness.load(id), HttpStatus.OK);
		} catch (BusinessException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(), e);
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage(), e);
		}
	}

	@PostMapping("")
	public ResponseEntity<Usuario> add(@RequestBody Usuario usuario) {
		try {
			usuarioBusiness.add(usuario);

			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", "/usuarios/" + usuario.getIdUser());
			return new ResponseEntity<Usuario>(responseHeaders, HttpStatus.CREATED);
		} catch (BusinessException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(), e);
		}
	}

	@PutMapping("")
	public ResponseEntity<Usuario> update(@RequestBody Usuario usuario) {
		try {
			return new ResponseEntity<Usuario>(usuarioBusiness.update(usuario), HttpStatus.OK);
		} catch (BusinessException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(), e);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") long id) {
		try {
			usuarioBusiness.delete(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(), e);
		}
	}

}

package com.at.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PropertySource({ "classpath:version.properties" })
public class CoreRestService extends BaseRestService {

	@Value("${app.version}")
	private String version;

	@GetMapping("/version")
	public ResponseEntity<String> version() {
		return new ResponseEntity<String>(version, HttpStatus.OK);
	}

	@GetMapping(Constantes.URL_DENY)
	public ResponseEntity<String> deny() {
		return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
	}

	@PostMapping(Constantes.URL_DENY)
	public ResponseEntity<String> denyPost() {
		return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
	}

	@GetMapping(Constantes.URL_LOGINOK)
	public ResponseEntity<String> loginok() {
		return new ResponseEntity<String>(userToJsonObject(getUserLogged()).toString(), HttpStatus.OK);
	}
	@PostMapping(Constantes.URL_LOGINOK)
	public ResponseEntity<String> loginokPost() {
		return new ResponseEntity<String>(userToJsonObject(getUserLogged()).toString(), HttpStatus.OK);
	}

	
	@GetMapping(Constantes.URL_AUTHINFO)
	public ResponseEntity<String> authInfo() {
		return loginok();
	}

	@GetMapping(Constantes.URL_LOGOUT)
	public ResponseEntity<String> logout() {
		return new ResponseEntity<String>("{}", HttpStatus.OK);
	}

	@GetMapping(value = Constantes.URL_TOKEN)
	public ResponseEntity<Object> getToken(@RequestParam("username") String username,
			@RequestParam("diasvalido") int diasvalido) {
		return genToken(username, diasvalido);
	}

}

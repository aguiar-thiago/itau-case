package br.com.itau.api.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.itau.api.security.service.JWTService;

@RestController
@RequestMapping("/v1/api")
public class JWTController {

	private static final String URI_VALIDATE = "/validate";

	@Autowired
	private JWTService service;

	@PostMapping(URI_VALIDATE)
	public ResponseEntity<Boolean> validateJwt(@RequestParam String token) {
		return ResponseEntity.ok(service.validateJWT(token));
	}
}
package br.com.itau.api.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.itau.api.security.exception.JWTException;
import br.com.itau.api.security.response.ApiResponse;
import br.com.itau.api.security.service.JWTService;

@RestController
@RequestMapping("/v1/api")
public class JWTController {

	private static final String URI_VALIDATE = "/validate";

	@Autowired
	private JWTService service;

	@PostMapping(URI_VALIDATE)
	public ResponseEntity<ApiResponse> validateJwt(@RequestParam String token) {
		try {
			return ResponseEntity.ok(service.validateJWT(token));
		} catch (JWTException e) {
			return ResponseEntity.badRequest().body(new ApiResponse(e));
		}
	}
}
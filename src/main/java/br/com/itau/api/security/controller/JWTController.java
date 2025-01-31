package br.com.itau.api.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.itau.api.security.service.JWTService;

@RestController
@RequestMapping("/api")
public class JWTController {

    @Autowired
    private JWTService service;

    @PostMapping("/validate")
    public ResponseEntity<String> validateJWT(@RequestParam String token) {
        if (service.validateJWT(token)) {
            return ResponseEntity.ok("JWT Válido");
        } else {
            return ResponseEntity.badRequest().body("JWT Inválido");
        }
    }
}
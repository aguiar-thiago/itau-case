package br.com.itau.api.security.decode.impl;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.itau.api.security.decode.JWTDecoder;

@Component
public class Auth0JWTDecoder implements JWTDecoder {
	
    @Override
    public DecodedJWT decode(String token) {
        return JWT.decode(token);
    }
}
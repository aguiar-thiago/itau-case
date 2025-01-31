package br.com.itau.api.security.validator;

import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.itau.api.security.exception.JWTException;

public interface ClaimValidator {
	 boolean validate(DecodedJWT decodedJWT)  throws JWTException;
}

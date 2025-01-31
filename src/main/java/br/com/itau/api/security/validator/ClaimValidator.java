package br.com.itau.api.security.validator;

import com.auth0.jwt.interfaces.DecodedJWT;

public interface ClaimValidator {
	 boolean validate(DecodedJWT decodedJWT);
}

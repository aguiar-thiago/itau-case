package br.com.itau.api.security.validator.impl;

import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.itau.api.security.utils.JWTUtils;
import br.com.itau.api.security.validator.ClaimValidator;

public class NameClaimValidator implements ClaimValidator {

	@Override
	public boolean validate(DecodedJWT decodedJWT) {
		String name = decodedJWT.getClaim("Name").asString();
        return name != null && name.length() <= 256 && !JWTUtils.containsNumbers(name);
	}

}

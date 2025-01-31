package br.com.itau.api.security.validator.impl;

import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.itau.api.security.enumeration.ClaimKeyEnum;
import br.com.itau.api.security.exception.JWTException;
import br.com.itau.api.security.utils.JWTUtils;
import br.com.itau.api.security.validator.ClaimValidator;

public class NameClaimValidator implements ClaimValidator {

	@Override
	public boolean validate(DecodedJWT decodedJWT) throws JWTException {
		String name = decodedJWT.getClaim(ClaimKeyEnum.NAME.getKey()).asString();
		if (name != null && name.length() <= 256 && !JWTUtils.containsNumbers(name)) {
			return true;
		}

		throw new JWTException("Name incorreto", 400);
	}

}

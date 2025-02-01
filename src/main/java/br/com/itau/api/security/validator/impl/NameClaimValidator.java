package br.com.itau.api.security.validator.impl;

import org.springframework.stereotype.Component;

import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.itau.api.security.enumeration.ClaimKeyEnum;
import br.com.itau.api.security.exception.JWTException;
import br.com.itau.api.security.utils.JWTUtils;
import br.com.itau.api.security.validator.ClaimValidator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class NameClaimValidator implements ClaimValidator {

	@Override
	public boolean validate(DecodedJWT decodedJWT) throws JWTException {
		String name = decodedJWT.getClaim(ClaimKeyEnum.NAME.getKey()).asString();

		if (name == null || name.length() >= 256 || JWTUtils.containsNumbers(name)) {
			log.error("O claim NAME está diferente do esperado: {}", name);
			throw new JWTException("Name incorreto", 400);
		}

		return true;
	}

}

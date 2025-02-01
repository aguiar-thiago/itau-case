package br.com.itau.api.security.validator.impl;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.itau.api.security.enumeration.ClaimKeyEnum;
import br.com.itau.api.security.exception.JWTException;
import br.com.itau.api.security.validator.ClaimValidator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ClaimKeysValidator implements ClaimValidator {

	private static final Set<String> EXPECTED_KEYS = Set.of(
			ClaimKeyEnum.NAME.getKey(),
			ClaimKeyEnum.ROLE.getKey(),
			ClaimKeyEnum.SEED.getKey());

	@Override
	public boolean validate(DecodedJWT decodedJWT) throws JWTException {
		if (decodedJWT.getClaims().size() != 3) {
			log.error("Numero de claims passado diferente do esperado: {}", String.valueOf(decodedJWT.getClaims().size()));
			throw new JWTException("Número de claims diferente do esperado", 400);
		}

		Set<String> claimKeys = decodedJWT.getClaims().keySet();
		if (!claimKeys.equals(EXPECTED_KEYS) ) {
			log.error("Claims fornecido diferente do esperado: {}", claimKeys.toString());
			throw new JWTException("claim nao mapeado", 400);
		}
		
		return true;
	}

}

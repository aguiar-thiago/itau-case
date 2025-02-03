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
	
	private static final int EXPECTED_CLAIMS_COUNT = 3;

	private static final Set<String> EXPECTED_KEYS = Set.of(
			ClaimKeyEnum.NAME.getKey(),
			ClaimKeyEnum.ROLE.getKey(),
			ClaimKeyEnum.SEED.getKey());

	@Override
	public void validate(DecodedJWT decodedJWT) throws JWTException {
		var sizeClaims = decodedJWT.getClaims().size();
		if (decodedJWT.getClaims().size() != EXPECTED_CLAIMS_COUNT) {
			log.error("Numero de claims passado diferente do esperado: Esperado: {}, mas encontrado: {}", EXPECTED_CLAIMS_COUNT,  sizeClaims);
			throw new JWTException("Claims fornecidos diferentes do esperado. Esperado: " + EXPECTED_CLAIMS_COUNT + ", mas encontrado: " + sizeClaims);
		}
		
		Set<String> claimKeys = decodedJWT.getClaims().keySet();
		if (!claimKeys.equals(EXPECTED_KEYS) ) {
			log.error("Claims enviados não é o esperado! Favor validar. Claims: {}", claimKeys.toString());
			throw new JWTException("Claims enviados não é o esperado! Favor validar se todos foram enviados.");
		}
		
	}

}

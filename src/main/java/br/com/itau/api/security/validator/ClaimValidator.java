package br.com.itau.api.security.validator;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.itau.api.security.enumeration.ClaimKeyEnum;
import br.com.itau.api.security.exception.JWTException;

public interface ClaimValidator {

	Logger log = LoggerFactory.getLogger(ClaimValidator.class);

	void validate(DecodedJWT decodedJWT) throws JWTException;

	default void isEmptyValue(ClaimKeyEnum claimKey, String value) throws JWTException {
		if (StringUtils.isEmpty(value)) {
			log.error("O valor claim {} não pode ser vazio/nulo", claimKey.getKey());
			throw new JWTException("O valor do claim não pode ser vazio ou nulo.");
		}
	}

}

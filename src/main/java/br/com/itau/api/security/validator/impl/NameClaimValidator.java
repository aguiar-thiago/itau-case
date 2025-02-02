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

    private static final int MAX_NAME_LENGTH = 256;

    @Override
    public void validate(DecodedJWT decodedJWT) throws JWTException {
        String name = decodedJWT.getClaim(ClaimKeyEnum.NAME.getKey()).asString();

        isEmptyValue(ClaimKeyEnum.NAME, name);

        if (name.length() >= MAX_NAME_LENGTH) {
            log.error("O claim NAME excede o tamanho máximo permitido. Valor recebido: {}", name);
            throw new JWTException("O valor do claim NAME excede o tamanho máximo permitido.");
        }

        if (JWTUtils.containsNumbers(name)) {
            log.error("O claim NAME contém números, o que não é permitido. Valor recebido: {}", name);
            throw new JWTException("O valor do claim NAME não pode conter números.");
        }
    }
}
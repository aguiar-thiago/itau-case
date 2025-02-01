package br.com.itau.api.security.validator.impl;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.itau.api.security.enumeration.ClaimKeyEnum;
import br.com.itau.api.security.enumeration.RoleEnum;
import br.com.itau.api.security.exception.JWTException;
import br.com.itau.api.security.validator.ClaimValidator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RoleClaimValidator implements ClaimValidator {
	
	private static final Set<String> VALID_ROLES = Set.of(
			RoleEnum.ADMIN.getValue(), 
	        RoleEnum.MEMBER.getValue(), 
	        RoleEnum.EXTERNAL.getValue()
	    );

    @Override
    public void validate(DecodedJWT decodedJWT) throws JWTException {
        String role = decodedJWT.getClaim(ClaimKeyEnum.ROLE.getKey()).asString();

        if (StringUtils.isEmpty(role) || !VALID_ROLES.contains(role)) {
        	log.error("O valor do claim ROLE não está mapeado ou é inválido: Role: {}", role);
        	throw new JWTException("O valor do claim ROLE não está mapeado.", 400);
        }
    }

}

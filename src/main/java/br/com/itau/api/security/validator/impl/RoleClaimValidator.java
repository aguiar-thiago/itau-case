package br.com.itau.api.security.validator.impl;

import java.util.Set;

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
    public boolean validate(DecodedJWT decodedJWT) throws JWTException {
        String role = decodedJWT.getClaim(ClaimKeyEnum.ROLE.getKey()).asString();

        if (role == null || !VALID_ROLES.contains(role)) {
        	log.error("O valor do claim ROLE não está mapeado: {}", role);
        	throw new JWTException("Role incorreto", 400);
        }
        
        return true;
    }

}

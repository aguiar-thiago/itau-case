package br.com.itau.api.security.validator.impl;

import java.util.Set;

import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.itau.api.security.enumeration.ClaimKeyEnum;
import br.com.itau.api.security.enumeration.RoleEnum;
import br.com.itau.api.security.validator.ClaimValidator;

public class RoleClaimValidator implements ClaimValidator {
	
	private static final Set<String> VALID_ROLES = Set.of(
	        RoleEnum.ADMIN.getValue(), 
	        RoleEnum.MEMBER.getValue(), 
	        RoleEnum.EXTERNAL.getValue()
	    );

    @Override
    public boolean validate(DecodedJWT decodedJWT) {
        String role = decodedJWT.getClaim(ClaimKeyEnum.ROLE.getKey()).asString();
        
        if (role == null || !VALID_ROLES.contains(role)) {
        	return false;
        }
        
        return true;

    }

}

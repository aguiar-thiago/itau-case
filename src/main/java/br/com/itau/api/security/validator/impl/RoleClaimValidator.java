package br.com.itau.api.security.validator.impl;

import java.util.Set;

import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.itau.api.security.validator.ClaimValidator;

public class RoleClaimValidator implements ClaimValidator {

	@Override
	public boolean validate(DecodedJWT decodedJWT) {
		String role = decodedJWT.getClaim("Role").asString();
        return role != null && Set.of("Admin", "Member", "External").contains(role);
	}

}

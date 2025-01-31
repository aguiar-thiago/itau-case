package br.com.itau.api.security.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.itau.api.security.validator.ClaimValidator;
import br.com.itau.api.security.validator.impl.ClaimKeysValidator;
import br.com.itau.api.security.validator.impl.NameClaimValidator;
import br.com.itau.api.security.validator.impl.RoleClaimValidator;
import br.com.itau.api.security.validator.impl.SeedClaimValidator;

@Service
public class JWTService {
	
	private final List<ClaimValidator> claimValidators;

    public JWTService() {
        this.claimValidators = List.of(
        	new ClaimKeysValidator(),
            new NameClaimValidator(),
            new RoleClaimValidator(),
            new SeedClaimValidator()
        );
    }

    public boolean validateJWT(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);

            for (ClaimValidator validator : claimValidators) {
                if (!validator.validate(decodedJWT)) {
                    return false;
                }
            }

            return true;
        } catch (JWTDecodeException | IllegalArgumentException e) {
            return false;
        }
    }

}


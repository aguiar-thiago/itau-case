package br.com.itau.api.security.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.itau.api.security.exception.JWTException;
import br.com.itau.api.security.response.ApiResponse;
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

	public ApiResponse validateJWT(String token) throws JWTException {
		try {
			DecodedJWT decodedJWT = JWT.decode(token);

			for (ClaimValidator validator : claimValidators) {
				validator.validate(decodedJWT);
			}

			return new ApiResponse("sucesso", 200);
		} catch (JWTDecodeException e) {
			throw new JWTException("Erro no decode", 400);
			
		} catch (JWTException e) {
			throw e;
			
		} catch (Exception e) {
			throw new JWTException("Erro inesperado", 500);
		}

	}

}

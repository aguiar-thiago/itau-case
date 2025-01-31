package br.com.itau.api.security.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.itau.api.security.utils.JWTUtils;

@Service
public class JWTService {
	public boolean validateJWT(String token) {
		try {
			DecodedJWT decodedJWT = JWT.decode(token);

			if (decodedJWT.getClaims().size() != 3) {
				return false;
			}

			String name = decodedJWT.getClaim("Name").asString();
			if (name == null || name.length() > 256 || JWTUtils.containsNumbers(name)) {
				return false;
			}

			String role = decodedJWT.getClaim("Role").asString();
			if (role == null || !Set.of("Admin", "Member", "External").contains(role)) {
				return false;
			}

			int seed = Integer.valueOf(decodedJWT.getClaim("Seed").asString());
			if (!JWTUtils.isPrime(seed)) {
				return false;
			}

			return true;
		} catch (JWTDecodeException | IllegalArgumentException e) {
			return false;
		}
	}

}

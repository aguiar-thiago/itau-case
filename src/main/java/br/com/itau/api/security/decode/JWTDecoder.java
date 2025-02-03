package br.com.itau.api.security.decode;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

public interface JWTDecoder {
	
	  DecodedJWT decode(String token) throws JWTDecodeException;

}

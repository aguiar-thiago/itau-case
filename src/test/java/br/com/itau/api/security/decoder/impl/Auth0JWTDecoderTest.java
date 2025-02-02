package br.com.itau.api.security.decoder.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.itau.api.security.decode.impl.Auth0JWTDecoder;

public class Auth0JWTDecoderTest {

	private Auth0JWTDecoder auth0JWTDecoder;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		auth0JWTDecoder = new Auth0JWTDecoder();
	}

	@Test
	void testDecode_Success() {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg";

		DecodedJWT result = auth0JWTDecoder.decode(token);

		assertNotNull(result);
	}
}

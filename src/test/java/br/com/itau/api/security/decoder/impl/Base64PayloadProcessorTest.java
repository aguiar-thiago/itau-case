package br.com.itau.api.security.decoder.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import br.com.itau.api.security.decode.impl.Base64PayloadProcessor;

public class Base64PayloadProcessorTest {

	private Base64PayloadProcessor base64PayloadProcessor;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		base64PayloadProcessor = new Base64PayloadProcessor();
	}

	@Test
	void testDecode_Success() {
		String token = "eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9";

		String result = base64PayloadProcessor.decodePayload(token);

		assertNotNull(result);
	}

}

package br.com.itau.api.security.decode.impl;

import java.util.Base64;

import org.springframework.stereotype.Component;

import br.com.itau.api.security.decode.Base64Decoder;

@Component
public class Base64PayloadProcessor implements Base64Decoder {

	@Override
	public String decodePayload(String payload) {
		return new String(Base64.getDecoder().decode(payload));
	}

}

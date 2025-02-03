package br.com.itau.api.security.decode;

public interface Base64Decoder {
	
	String decodePayload(String payload);
	
}

package br.com.itau.api.security.exception;

public class JWTException extends Exception {

	private static final long serialVersionUID = 1L;

    public JWTException(String message) {
    	super(message);
    }

}

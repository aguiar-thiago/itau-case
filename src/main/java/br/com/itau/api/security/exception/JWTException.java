package br.com.itau.api.security.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JWTException extends Exception {

	private static final long serialVersionUID = 1L;
	private int errorCode;

    public JWTException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}

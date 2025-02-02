package br.com.itau.api.security.response;

import br.com.itau.api.security.exception.JWTException;
import lombok.Getter;

@Getter
public class ApiResponse {
	
	private int code;
    private boolean validJWT;
    private String message;
    
    public ApiResponse(String message, int code) {
        this.code = code;
        this.validJWT = true;
        this.message = message;
    }

    public ApiResponse(JWTException e) {
        this.code = e.getErrorCode();
        this.validJWT = false;
        this.message = e.getMessage();
    }

}

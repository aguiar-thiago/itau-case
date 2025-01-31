package br.com.itau.api.security.response;

import br.com.itau.api.security.exception.JWTException;
import lombok.Getter;

@Getter
public class ApiResponse {
	
	private int code;
    private boolean success;
    private String message;
    
    public ApiResponse(String message, int code) {
        this.code = code;
        this.success = true;
        this.message = message;
    }

    public ApiResponse(JWTException e) {
        this.code = e.getErrorCode();
        this.success = false;
        this.message = e.getMessage();
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
               "code=" + code +
               ", success=" + success +
               ", message='" + message + '\'' +
               '}';
    }

}

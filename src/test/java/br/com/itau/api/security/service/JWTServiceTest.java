package br.com.itau.api.security.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import br.com.itau.api.security.decode.Base64Decoder;
import br.com.itau.api.security.decode.JWTDecoder;
import br.com.itau.api.security.exception.JWTException;
import br.com.itau.api.security.response.ApiResponse;
import br.com.itau.api.security.validator.ClaimValidator;

import java.util.Arrays;
import java.util.List;

public class JWTServiceTest {

    @Mock
    private JWTDecoder jwtDecoder;

    @Mock
    private Base64Decoder base64Decoder;

    @Mock
    private ClaimValidator claimValidator;

    private JWTService jwtService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        List<ClaimValidator> claimValidators = Arrays.asList(claimValidator);
        jwtService = new JWTService(claimValidators, jwtDecoder, base64Decoder);
    }

    @Test
    void testValidateJWT_Success() throws Exception {
        String token = "validToken";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(jwtDecoder.decode(token)).thenReturn(decodedJWT);
        when(base64Decoder.decodePayload(anyString())).thenReturn("decodedPayload");
        doNothing().when(claimValidator).validate(decodedJWT);

        ApiResponse response = jwtService.validateJWT(token);

        assertEquals(200, response.getCode());
        assertEquals("JWT válido!", response.getMessage());
    }
    
    @Test
    void testValidateJWT_JWTException() throws Exception {
        String token = "validToken";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(jwtDecoder.decode(token)).thenReturn(decodedJWT);
        when(base64Decoder.decodePayload(anyString())).thenReturn("decodedPayload");
        
        doThrow(new JWTException("Erro na validação do claim", 400)).when(claimValidator).validate(decodedJWT);


        JWTException exception = assertThrows(JWTException.class, () -> {
            jwtService.validateJWT(token);
        });

        assertEquals("Erro na validação do claim", exception.getMessage());
        assertEquals(400, exception.getErrorCode());
    }

    @Test
    void testValidateJWT_Fail_DecodeException() {
        String token = "invalidToken";
        when(jwtDecoder.decode(token)).thenThrow(new JWTDecodeException("Erro ao decodificar o JWT"));

        JWTException exception = assertThrows(JWTException.class, () -> {
            jwtService.validateJWT(token);
        });

        assertEquals("Erro ao decodificar o JWT!", exception.getMessage());
        assertEquals(400, exception.getErrorCode());
    }

    @Test
    void testValidateJWT_Fail_GeneralException() throws Exception {
        String token = "validToken";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(jwtDecoder.decode(token)).thenReturn(decodedJWT);
        when(base64Decoder.decodePayload(anyString())).thenReturn("decodedPayload");
        doThrow(new RuntimeException("Erro inesperado")).when(claimValidator).validate(decodedJWT);

        JWTException exception = assertThrows(JWTException.class, () -> {
            jwtService.validateJWT(token);
        });

        assertEquals("Erro inesperado", exception.getMessage());
        assertEquals(500, exception.getErrorCode());
    }
    
}

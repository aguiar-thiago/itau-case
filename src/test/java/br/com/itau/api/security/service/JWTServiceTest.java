package br.com.itau.api.security.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.itau.api.security.decode.Base64Decoder;
import br.com.itau.api.security.decode.JWTDecoder;
import br.com.itau.api.security.exception.JWTException;
import br.com.itau.api.security.validator.ClaimValidator;

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

        assertEquals(true, jwtService.validateJWT(token));
    }
    
    @Test
    void testValidateJWT_JWTException() throws Exception {
        String token = "validToken";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(jwtDecoder.decode(token)).thenReturn(decodedJWT);
        when(base64Decoder.decodePayload(anyString())).thenReturn("decodedPayload");
        
        doThrow(new JWTException("Erro na validação do claim")).when(claimValidator).validate(decodedJWT);

        assertEquals(false, jwtService.validateJWT(token));
    }

    @Test
    void testValidateJWT_Fail_DecodeException() {
        String token = "invalidToken";
        when(jwtDecoder.decode(token)).thenThrow(new JWTDecodeException("Erro ao decodificar o JWT"));

        assertEquals(false, jwtService.validateJWT(token));
    }

    @Test
    void testValidateJWT_Fail_GeneralException() throws Exception {
        String token = "validToken";
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(jwtDecoder.decode(token)).thenReturn(decodedJWT);
        when(base64Decoder.decodePayload(anyString())).thenReturn("decodedPayload");
        doThrow(new RuntimeException("Erro inesperado")).when(claimValidator).validate(decodedJWT);

        assertEquals(false, jwtService.validateJWT(token));
    }
    
}

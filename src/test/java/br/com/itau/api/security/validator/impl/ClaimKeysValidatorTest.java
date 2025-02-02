package br.com.itau.api.security.validator.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.itau.api.security.exception.JWTException;

public class ClaimKeysValidatorTest {

    @Mock
    private DecodedJWT decodedJWT;

    @Mock
    private Claim claim;

    private ClaimKeysValidator claimKeysValidator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        claimKeysValidator = new ClaimKeysValidator();
    }

    @Test
    void testValidate_Success() {
        when(decodedJWT.getClaims()).thenReturn(Map.of(
                "Name", claim,
                "Role", claim,
                "Seed", claim
        ));

        assertDoesNotThrow(() -> claimKeysValidator.validate(decodedJWT));
    }

    @Test
    void testValidate_InvalidClaimCount() {
        when(decodedJWT.getClaims()).thenReturn(Map.of(
                "Name", claim,
                "Role", claim
        ));

        JWTException exception = assertThrows(JWTException.class, () -> {
            claimKeysValidator.validate(decodedJWT);
        });

        assertEqualsReturnMessage("Claims fornecidos diferentes do esperado. Esperado: 3, mas encontrado: 2", 400, exception);

    }

    @Test
    void testValidate_InvalidClaimKeys() {
        when(decodedJWT.getClaims()).thenReturn(Map.of(
                "Name", claim,
                "Role", claim,
                "IncorrectKey", claim
        ));

        JWTException exception = assertThrows(JWTException.class, () -> {
            claimKeysValidator.validate(decodedJWT);
        }); 

        assertEqualsReturnMessage("Claims enviados não é o esperado! Favor validar se todos foram enviados.", 400, exception);
    }
        
    private void assertEqualsReturnMessage(String message, int expectedCode, JWTException exception) {
    	assertEquals(message, exception.getMessage());
        assertEquals(expectedCode, exception.getErrorCode());
    }
}
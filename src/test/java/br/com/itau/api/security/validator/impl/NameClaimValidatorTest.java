package br.com.itau.api.security.validator.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.itau.api.security.enumeration.ClaimKeyEnum;
import br.com.itau.api.security.exception.JWTException;

public class NameClaimValidatorTest {

    @Mock
    private DecodedJWT decodedJWT;

    @Mock
    private Claim claim;
    
    private NameClaimValidator nameClaimValidator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        nameClaimValidator = new NameClaimValidator();
    }

    @Test
    void testValidate_Success() {
        String validName = "ValidName";
        
        mockClaimName(validName);

        assertDoesNotThrow(() -> nameClaimValidator.validate(decodedJWT));
    }

    @Test
    void testValidate_EmptyName() {
        String emptyName = "";

        mockClaimName(emptyName);

        JWTException exception = assertThrows(JWTException.class, () -> {
            nameClaimValidator.validate(decodedJWT);
        });
        
        assertEqualsReturnMessage("O valor do claim NAME não pode ser vazio ou nulo.", 400, exception);
    }

    @Test
    void testValidate_NameExceedsMaxLength() {
        String longName = "A".repeat(257);

        mockClaimName(longName);

        JWTException exception = assertThrows(JWTException.class, () -> {
            nameClaimValidator.validate(decodedJWT);
        });

        assertEqualsReturnMessage("O valor do claim NAME excede o tamanho máximo permitido.", 400, exception);
    }

    @Test
    void testValidate_NameContainsNumbers() {
        String nameWithNumbers = "Name123";

        mockClaimName(nameWithNumbers);

        JWTException exception = assertThrows(JWTException.class, () -> {
            nameClaimValidator.validate(decodedJWT);
        });

        assertEqualsReturnMessage("O valor do claim NAME não pode conter números.", 400, exception);
        
    }
    
    private void mockClaimName(String returnClaimName) {
        when(decodedJWT.getClaim(ClaimKeyEnum.NAME.getKey())).thenReturn(claim);
        when(claim.asString()).thenReturn(returnClaimName);
    }
    
    private void assertEqualsReturnMessage(String message, int expectedCode, JWTException exception) {
    	assertEquals(message, exception.getMessage());
        assertEquals(expectedCode, exception.getErrorCode());
    }


}
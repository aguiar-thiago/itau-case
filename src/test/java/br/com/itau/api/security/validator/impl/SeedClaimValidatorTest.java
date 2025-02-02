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

public class SeedClaimValidatorTest {

    @Mock
    private DecodedJWT decodedJWT;
    
    @Mock
    private Claim claim;

    private SeedClaimValidator seedClaimValidator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        seedClaimValidator = new SeedClaimValidator();
    }

    @Test
    void testValidate_Success() {
        String validSeed = "7";
        
        mockClaimSeed(validSeed); 
        
        assertDoesNotThrow(() -> seedClaimValidator.validate(decodedJWT));
    }

    @Test
    void testValidate_EmptySeed() {
        String emptySeed = "";

        mockClaimSeed(emptySeed);
        
        JWTException exception = assertThrows(JWTException.class, () -> {
            seedClaimValidator.validate(decodedJWT);
        });

        assertEqualsReturnMessage("Seed incorreto", 400, exception);
    }

    @Test
    void testValidate_NonNumericSeed() {
        String nonNumericSeed = "abc";
        
        mockClaimSeed(nonNumericSeed);
        
        JWTException exception = assertThrows(JWTException.class, () -> {
            seedClaimValidator.validate(decodedJWT);
        });

        assertEqualsReturnMessage("Seed incorreto", 400, exception);
    }

    @Test
    void testValidate_NonPrimeSeed() {
        String nonPrimeSeed = "4";
        
        mockClaimSeed(nonPrimeSeed);
        
        JWTException exception = assertThrows(JWTException.class, () -> {
            seedClaimValidator.validate(decodedJWT);
        });

        assertEqualsReturnMessage("Seed incorreto", 400, exception);
        
    }
    
    private void mockClaimSeed(String returnClaim) {
        when(decodedJWT.getClaim(ClaimKeyEnum.SEED.getKey())).thenReturn(claim);
        when(claim.asString()).thenReturn(returnClaim);
    }
    
    private void assertEqualsReturnMessage(String message, int expectedCode, JWTException exception) {
    	assertEquals(message, exception.getMessage());
        assertEquals(expectedCode, exception.getErrorCode());
    }
    
}

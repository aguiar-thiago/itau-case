package br.com.itau.api.security.validator.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Set;

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
    
    private static final Set<String> LIST_VALUES_FAILURE = Set.of("", "abc", "4");

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        seedClaimValidator = new SeedClaimValidator();
    }

    @Test
    void testValidate_Success() {
    	var validSeed = "7";
        mockClaimSeed(validSeed); 
        assertDoesNotThrow(() -> seedClaimValidator.validate(decodedJWT));
    }
    
    @Test
    void testValidate_IncorrectValues() {
        for (var value: LIST_VALUES_FAILURE) {
        	mockClaimSeed(value);
        	JWTException exception = generateException();
        	assertEqualsReturnMessage(exception); 
        }        
    }
    
    private JWTException generateException() {
		return assertThrows(JWTException.class, () -> {
            seedClaimValidator.validate(decodedJWT);
        });
	}

	private void mockClaimSeed(String returnClaim) {
        when(decodedJWT.getClaim(ClaimKeyEnum.SEED.getKey())).thenReturn(claim);
        when(claim.asString()).thenReturn(returnClaim);
    }
    
    private void assertEqualsReturnMessage(JWTException exception) {
    	 assertEquals(JWTException.class, exception.getClass());
    }
    
}

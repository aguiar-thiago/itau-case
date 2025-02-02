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

public class NameClaimValidatorTest {

    @Mock
    private DecodedJWT decodedJWT;

    @Mock
    private Claim claim;
    
    private NameClaimValidator nameClaimValidator;
    
    private static final Set<String> LIST_VALUES_FAILURE = Set.of("Name123", "A".repeat(257), "");

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
    void testValidate_IncorrectValues() {
        for (String value: LIST_VALUES_FAILURE) {
        	mockClaimName(value);
        	JWTException exception = generateException();
        	assertEqualsReturnMessage(400, exception);
        }        
    }
    
    private void mockClaimName(String returnClaimName) {
        when(decodedJWT.getClaim(ClaimKeyEnum.NAME.getKey())).thenReturn(claim);
        when(claim.asString()).thenReturn(returnClaimName);
    }
    
    private JWTException generateException() {
		return assertThrows(JWTException.class, () -> {
            nameClaimValidator.validate(decodedJWT);
        });
	}
    
    private void assertEqualsReturnMessage(int expectedCode, JWTException exception) {
        assertEquals(expectedCode, exception.getErrorCode());
    }


}
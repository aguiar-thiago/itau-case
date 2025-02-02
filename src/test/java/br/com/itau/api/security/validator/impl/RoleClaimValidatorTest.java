package br.com.itau.api.security.validator.impl;

import static org.mockito.Mockito.*;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import br.com.itau.api.security.exception.JWTException;
import br.com.itau.api.security.enumeration.ClaimKeyEnum;
import br.com.itau.api.security.enumeration.RoleEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

public class RoleClaimValidatorTest {

    @Mock
    private DecodedJWT decodedJWT;
    
    @Mock
    private Claim claim;

    private RoleClaimValidator roleClaimValidator;
    
    private static final Set<String> LIST_VALUES_FAILURE = Set.of("INVALID_ROLE", "");

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        roleClaimValidator = new RoleClaimValidator();
    }

    @Test
    void testValidate_Success() {
        String validRole = RoleEnum.ADMIN.getValue();

        mockClaimRole(validRole);

        assertDoesNotThrow(() -> roleClaimValidator.validate(decodedJWT));
    }
    
    @Test
    void testValidate_IncorrectValues() {
        for (String value: LIST_VALUES_FAILURE) {
        	mockClaimRole(value);
        	JWTException exception = generateException();
        	assertEqualsReturnMessage(exception);
        }        
    }
    
    private void mockClaimRole(String returnClaim) {
        when(decodedJWT.getClaim(ClaimKeyEnum.ROLE.getKey())).thenReturn(claim);
        when(claim.asString()).thenReturn(returnClaim);
    }
    
    private JWTException generateException() {
		return assertThrows(JWTException.class, () -> {
            roleClaimValidator.validate(decodedJWT);
        });
	}
    
    private void assertEqualsReturnMessage(JWTException exception) {
    	assertEquals(JWTException.class, exception.getClass());;
    }
    
}

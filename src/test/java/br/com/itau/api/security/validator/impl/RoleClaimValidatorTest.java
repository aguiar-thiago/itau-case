package br.com.itau.api.security.validator.impl;

import static org.mockito.Mockito.*;
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
    void testValidate_InvalidRole() {
        String invalidRole = "INVALID_ROLE";

        mockClaimRole(invalidRole);

        JWTException exception = assertThrows(JWTException.class, () -> {
            roleClaimValidator.validate(decodedJWT);
        });

        assertEqualsReturnMessage("O valor do claim ROLE não está mapeado.", 400, exception);
    }

    @Test
    void testValidate_EmptyRole() {
        String emptyRole = "";

        mockClaimRole(emptyRole);
        
        JWTException exception = assertThrows(JWTException.class, () -> {
            roleClaimValidator.validate(decodedJWT);
        });

        assertEqualsReturnMessage("O valor do claim ROLE não está mapeado.", 400, exception);
    }

    @Test
    void testValidate_NullRole() {
    	mockClaimRole(null);

        JWTException exception = assertThrows(JWTException.class, () -> {
            roleClaimValidator.validate(decodedJWT);
        });
        
        assertEqualsReturnMessage("O valor do claim ROLE não está mapeado.", 400, exception);
    }
    
    private void mockClaimRole(String returnClaim) {
        when(decodedJWT.getClaim(ClaimKeyEnum.ROLE.getKey())).thenReturn(claim);
        when(claim.asString()).thenReturn(returnClaim);
    }
    
    private void assertEqualsReturnMessage(String message, int expectedCode, JWTException exception) {
    	assertEquals(message, exception.getMessage());
        assertEquals(expectedCode, exception.getErrorCode());
    }
    
}

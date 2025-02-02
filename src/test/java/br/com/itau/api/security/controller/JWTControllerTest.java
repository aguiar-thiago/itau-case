package br.com.itau.api.security.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.itau.api.security.exception.JWTException;
import br.com.itau.api.security.response.ApiResponse;
import br.com.itau.api.security.service.JWTService;

public class JWTControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private JWTService jwtService;

    @InjectMocks
    private JWTController jwtController;

    private static final String TOKEN_BAD_REQUEST = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiTWVtYmVyIiwiT3JnIjoiQlIiLCJTZWVkIjoiMTQ2MjciLCJOYW1lIjoiVmFsZGlyIEFyYW5oYSJ9.cmrXV_Flm5mfdpfNUVopY_I2zeJUy4EZ4i3Fea98zvY";
    private static final String TOKEN_SUCCESS_REQUEST = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg";
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(jwtController).build();
    }

    @Test
    void testValidateJwt_Success() throws Exception {
        ApiResponse response = new ApiResponse("JWT válido!", 200);
        
        when(jwtService.validateJWT(anyString())).thenReturn(response);

        mockMvc.perform(post("/v1/api/validate")
                        .param("token", TOKEN_SUCCESS_REQUEST)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("JWT válido!"));
    }

    @Test
    void testValidateJwt_Error() throws Exception {
        JWTException jwtException = new JWTException("Erro ao validar JWT", 400);
        when(jwtService.validateJWT(anyString())).thenThrow(jwtException);

        mockMvc.perform(post("/v1/api/validate")
                        .param("token", TOKEN_BAD_REQUEST)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Erro ao validar JWT"));
                
    }
}
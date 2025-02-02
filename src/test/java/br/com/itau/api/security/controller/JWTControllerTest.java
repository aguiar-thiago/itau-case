package br.com.itau.api.security.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import br.com.itau.api.security.service.JWTService;

public class JWTControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private JWTService jwtService;

    @InjectMocks
    private JWTController jwtController;

    private static final String TOKEN_SUCCESS_REQUEST = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg";
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(jwtController).build();
    }

    @Test
    void testValidateJwt_Success() throws Exception {
        boolean response = true;
        
        when(jwtService.validateJWT(anyString())).thenReturn(response);

        mockMvc.perform(post("/v1/api/validate")
                        .param("token", TOKEN_SUCCESS_REQUEST)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
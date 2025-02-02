package br.com.itau.api.security.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.itau.api.security.decode.Base64Decoder;
import br.com.itau.api.security.decode.JWTDecoder;
import br.com.itau.api.security.exception.JWTException;
import br.com.itau.api.security.response.ApiResponse;
import br.com.itau.api.security.validator.ClaimValidator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JWTService {

	private final JWTDecoder jWTDecoder;
	private final Base64Decoder base64Decoder;
    private final List<ClaimValidator> claimValidators;

    public JWTService(List<ClaimValidator> claimValidators, JWTDecoder jWTDecoder, Base64Decoder base64Decoder) {
        this.jWTDecoder = jWTDecoder;
        this.base64Decoder = base64Decoder;
        this.claimValidators = claimValidators;
    }

    public ApiResponse validateJWT(String token) throws JWTException {
        try {
            log.info("Iniciando decode token: {}", token);
            DecodedJWT decodedJWT = jWTDecoder.decode(token);

            String jsonValue = base64Decoder.decodePayload(decodedJWT.getPayload());
            log.info("JSON decodificado {}", jsonValue);

            for (ClaimValidator validator : claimValidators) {
                validator.validate(decodedJWT);
            }

            return new ApiResponse("JWT valido!", 200);
        } catch (JWTDecodeException e) {
            log.error("Nao foi possivel decodificar o JWT!");
            throw new JWTException("Erro ao decodificar o JWT!", 400);
        } catch (JWTException e) {
            throw e;
        } catch (Exception e) {
            log.error("Erro: ", e);
            throw new JWTException("Erro inesperado", 500);
        }
    }
}
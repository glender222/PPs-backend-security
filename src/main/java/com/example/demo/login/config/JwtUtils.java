package com.example.demo.login.config;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import lombok.extern.slf4j.Slf4j;



@Component
@Slf4j
public class JwtUtils {
 
    @Value("${security.jwt.key.private}")
    private String privateKey;



    @Value("${security.jwt.user.generator}")
    private String userGenerator;




    public String createToken(Authentication authentication) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.privateKey);
            String username = authentication.getPrincipal().toString();
            String authorities = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(","));


            return JWT.create()
                    .withIssuer(this.userGenerator)
                    .withSubject(username)
                    .withClaim("authorities", authorities)
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 3600000))
                    .withJWTId(UUID.randomUUID().toString())
                    .sign(algorithm);
        } catch (Exception e) {
            throw new RuntimeException("Could not create token", e);
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    public DecodedJWT validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.privateKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(this.userGenerator)
                    .build();

            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT;
        } catch (JWTVerificationException exception) {
            String errorMessage = String.format("Token validation failed: %s", exception.getMessage());
            logger.error(errorMessage, exception);

            
            throw new JWTVerificationException("Token invalid, not Authorized");
        }

    }

    public String extractUsername(DecodedJWT decodedJWT){
        return decodedJWT.getSubject().toString();
    }

    public Claim getSpecificClaim(DecodedJWT decodedJWT, String claimName) {
        return decodedJWT.getClaim(claimName);
    }
    public Map<String, Claim> obtenerTodasLasReclamaciones(DecodedJWT tokenDecodificado) {
        return tokenDecodificado.getClaims();
    }
    
}

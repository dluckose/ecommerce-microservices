package com.ecommerce.productservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import javax.crypto.spec.SecretKeySpec;

@Configuration
public class JwtConfig {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Bean
    public JwtDecoder jwtDecoder() {
        // Convert your string secret into a Key object
        byte[] keyBytes = jwtSecret.getBytes();
        SecretKeySpec secretKey = new SecretKeySpec(keyBytes, 0, keyBytes.length, "HmacSHA256");

        // Build the decoder using that secret key
        return NimbusJwtDecoder.withSecretKey(secretKey).build();
    }
}

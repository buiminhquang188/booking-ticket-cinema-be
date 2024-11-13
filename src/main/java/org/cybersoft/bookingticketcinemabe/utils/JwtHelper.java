package org.cybersoft.bookingticketcinemabe.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.cybersoft.bookingticketcinemabe.exception.runtime.AuthenticateException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Component
public class JwtHelper {
    @Value("${jwt.key}")
    private String SIGNER_KEY;

    @Value("${jwt.expirationMs}")
    private String expirationMsTime;


    public String generateToken(String subject, Map<String, Object> claims) {
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(SIGNER_KEY));

        return Jwts.builder()
                .issuer("Booking-ticket-cinema")
                .subject(subject)
                .claims(claims)
                .issuedAt(new Date())
                .expiration(new Date(Instant.now()
                        .plusMillis(Long.parseLong(expirationMsTime))
                        .toEpochMilli()))
                .signWith(secretKey)
                .compact();
    }

    public Claims parseToken(String token) {
        try {
            SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(SIGNER_KEY));
            return Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (Exception e) {
            throw new AuthenticateException("Invalid or expired JWT token");
        }
    }
}

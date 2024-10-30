package org.cybersoft.bookingticketcinemabe.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.cybersoft.bookingticketcinemabe.exception.runtime.AuthenticateException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

@Configuration
public class JwtHelper {
    @Value("${jwt.key}")
    private String SIGNER_KEY;
    //        Generate jwt key
//        SecretKey secretKey = Jwts.SIG.HS512.key().build();
//        String key = Encoders.BASE64.encode(secretKey.getEncoded());
//        System.out.println(key);
    private final SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(SIGNER_KEY));
    @Value("${jwt.expirationMs}")
    private int expirationMsTime;

    public String generateToken(String data) {


        String token = Jwts.builder()
                .issuer("Booking-ticket-cinema")
                .subject(data)
                .issuedAt(new Date())
                .expiration(new Date(Instant.now().plusMillis(expirationMsTime).toEpochMilli()))
                .signWith(secretKey)
                .compact();
        return token;
    }

    public String introspectToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return claims.getSubject();
        } catch (Exception e) {
            throw new AuthenticateException("Invalid or expired JWT token");
        }
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (MalformedJwtException exception) {
            throw new AuthenticateException("Invalid JWT token");
        } catch (ExpiredJwtException exception) {
            throw new AuthenticateException("Expired JWT token");
        } catch (UnsupportedJwtException exception) {
            throw new AuthenticateException("Unsupported JWT token");
        } catch (IllegalArgumentException exception) {
            throw new AuthenticateException("JWT claims string is empty.");
        }
    }
}

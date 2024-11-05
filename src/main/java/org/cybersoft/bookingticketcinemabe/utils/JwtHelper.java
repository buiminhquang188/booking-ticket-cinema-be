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

@Component
public class JwtHelper {
    @Value("${jwt.key}")
    private String SIGNER_KEY;

    @Value("${jwt.expirationMs}")
    private String expirationMsTime;


    public String generateToken(String data) {
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(SIGNER_KEY));

        return Jwts.builder()
                .issuer("Booking-ticket-cinema")
                .subject(data)
                .issuedAt(new Date())
                .expiration(new Date(Instant.now().plusMillis(Long.parseLong(expirationMsTime)).toEpochMilli()))
                .signWith(secretKey)
                .compact();
    }

    public String introspectToken(String token) {
        try {
            SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(SIGNER_KEY));
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
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(SIGNER_KEY));
        Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token);
        return true;

    }
//    public static void main(String[] args) {
//        //        Generate jwt key
//        SecretKey secretKey = Jwts.SIG.HS512.key().build();
//        String key = Encoders.BASE64URL.encode(secretKey.getEncoded());
//        System.out.println(key);
//
//    }
}

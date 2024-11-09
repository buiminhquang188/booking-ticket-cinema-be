package org.cybersoft.bookingticketcinemabe.controller.impl;

import lombok.AllArgsConstructor;
import org.cybersoft.bookingticketcinemabe.controller.AuthenticationController;
import org.cybersoft.bookingticketcinemabe.dto.user.UserDetailsCustom;
import org.cybersoft.bookingticketcinemabe.entity.UserEntity;
import org.cybersoft.bookingticketcinemabe.exception.runtime.AuthenticateException;
import org.cybersoft.bookingticketcinemabe.payload.request.authentication.AuthenticateRequest;
import org.cybersoft.bookingticketcinemabe.payload.response.BaseResponse;
import org.cybersoft.bookingticketcinemabe.utils.JwtHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class AuthenticationControllerImpl implements AuthenticationController {
    private final AuthenticationManager authenticationManager;

    private final JwtHelper jwtHelper;

    @Override
    public ResponseEntity<?> Authenticate(AuthenticateRequest request) {
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        if (!authentication.isAuthenticated()) {
            throw new AuthenticateException("Invalid user request!");
        }

        String email = authentication.getPrincipal()
                .toString();
        List<String> roles = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        Map<String, Object> claims = this.buildClaims(authentication, roles);
        String token = this.jwtHelper.generateToken(email, claims);

        return ResponseEntity.ok(
                BaseResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(HttpStatus.OK.getReasonPhrase())
                        .data(token)
                        .build()
        );
    }

    private Map<String, Object> buildClaims(Authentication authentication, List<String> roles) {
        UserDetailsCustom userDetailsCustom = (UserDetailsCustom) authentication.getDetails();
        UserEntity user = userDetailsCustom.getUserEntity();

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("roles", roles);

        return claims;
    }
}

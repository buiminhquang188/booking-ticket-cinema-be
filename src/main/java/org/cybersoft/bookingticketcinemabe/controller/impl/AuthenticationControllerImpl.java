package org.cybersoft.bookingticketcinemabe.controller.impl;

import lombok.AllArgsConstructor;
import org.cybersoft.bookingticketcinemabe.controller.AuthenticationController;
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

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class AuthenticationControllerImpl implements AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtHelper jwtHelper;

    @Override
    public ResponseEntity<?> Authenticate(AuthenticateRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );
        if (authentication.isAuthenticated()) {
            String email = authentication.getPrincipal().toString();
            List<String> roles = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority).collect(Collectors.toList());
            ;
            String emailAndRole = email + ":" + String.join(",", roles);
            ;
            String token = jwtHelper.generateToken(emailAndRole);
            return ResponseEntity.ok(
                    BaseResponse.builder()
                            .statusCode(HttpStatus.OK.value())
                            .message(HttpStatus.OK.getReasonPhrase())
                            .data(token)
                            .build()
            );
        } else {
            throw new AuthenticateException("Invalid user request!");
        }
    }
}

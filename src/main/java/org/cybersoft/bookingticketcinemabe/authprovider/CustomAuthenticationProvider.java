package org.cybersoft.bookingticketcinemabe.authprovider;

import lombok.RequiredArgsConstructor;
import org.cybersoft.bookingticketcinemabe.dto.user.UserDetailsCustom;
import org.cybersoft.bookingticketcinemabe.payload.request.authentication.AuthenticateRequest;
import org.cybersoft.bookingticketcinemabe.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final AuthenticationService authenticationService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String password = authentication.getCredentials()
                .toString();

        AuthenticateRequest request = new AuthenticateRequest(userName, password);
        UserDetailsCustom userDetails = this.authenticationService.checkLogin(request);

        if (userDetails != null) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    userName,
                    null,
                    userDetails.getAuthorities());

            usernamePasswordAuthenticationToken.setDetails(userDetails);

            return usernamePasswordAuthenticationToken;
        }

        throw new BadCredentialsException("Incorrect user credentials !!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

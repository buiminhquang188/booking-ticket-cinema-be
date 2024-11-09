package org.cybersoft.bookingticketcinemabe.filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.cybersoft.bookingticketcinemabe.utils.JwtHelper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtHelper jwtHelper;

    private final HandlerExceptionResolver handlerExceptionResolver;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        try {
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                Claims claims = this.jwtHelper.parseToken(token);

                if (claims != null) {
                    String email = claims.getSubject();
                    List<String> roles = claims.get("roles", List.class);
                    List<GrantedAuthority> authorities = new ArrayList<>();

                    roles.forEach(role -> {
                        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role);
                        authorities.add(simpleGrantedAuthority);
                    });

                    if (email != null && SecurityContextHolder.getContext()
                                                 .getAuthentication() == null) {
                        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(email, null, authorities);
                        SecurityContext context = SecurityContextHolder.getContext();
                        context.setAuthentication(authenticationToken);
                    }
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            handlerExceptionResolver.resolveException(request, response, null, e);
        }
    }
}

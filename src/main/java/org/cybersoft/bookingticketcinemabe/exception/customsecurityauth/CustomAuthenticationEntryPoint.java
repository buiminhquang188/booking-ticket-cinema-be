package org.cybersoft.bookingticketcinemabe.exception.customsecurityauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.cybersoft.bookingticketcinemabe.enums.Status;
import org.cybersoft.bookingticketcinemabe.payload.response.ErrorDetailResponse;
import org.cybersoft.bookingticketcinemabe.payload.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ErrorResponse<Object> errorResponse = ErrorResponse.builder()
                .status(Status.ERROR.toString()
                        .toLowerCase())
                .statusCode(HttpStatus.FORBIDDEN.getReasonPhrase())
                .error(
                        ErrorDetailResponse.builder()
                                .code(HttpStatus.FORBIDDEN.getReasonPhrase())
                                .details("Access denied. Please authenticate to access this resource.")
                                .message(authException.getMessage())
                                .timestamp(LocalDateTime.now())
                                .path(request.getRequestURI())
                                .build()
                )
                .build();

        String jsonResponse = objectMapper.writeValueAsString(errorResponse);
        response.getWriter().write(jsonResponse);
    }
}

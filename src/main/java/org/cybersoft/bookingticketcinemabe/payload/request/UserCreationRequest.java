package org.cybersoft.bookingticketcinemabe.payload.request;


import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserCreationRequest ( 
        boolean isEmailVerified,
        boolean isPhoneVerified,
        String phone,
         String role,
         String email,

         String firstName,

         String lastName,

         String fullName,

         String avatar,

         String password
){}


package org.cybersoft.bookingticketcinemabe.annotation.validator;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.cybersoft.bookingticketcinemabe.annotation.ValidSeatsValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidSeatsValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSeats {
    String message() default "Seats must be unique by row, column, and seat number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
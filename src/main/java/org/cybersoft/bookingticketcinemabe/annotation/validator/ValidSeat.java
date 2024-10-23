package org.cybersoft.bookingticketcinemabe.annotation.validator;

import jakarta.validation.Constraint;
import org.cybersoft.bookingticketcinemabe.annotation.ValidSeatValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidSeatValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSeat {
    String message() default "Cannot set price for inactive seat";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}

package org.cybersoft.bookingticketcinemabe.annotation.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.cybersoft.bookingticketcinemabe.annotation.OnlyOneOfValidator;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = OnlyOneOfValidator.class)
public @interface OnlyOneOf {
    String message() default "Only one of the specified fields can be provided.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] fields();
}

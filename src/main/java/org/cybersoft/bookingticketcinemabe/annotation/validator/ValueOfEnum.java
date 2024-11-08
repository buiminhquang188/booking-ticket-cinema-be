package org.cybersoft.bookingticketcinemabe.annotation.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.cybersoft.bookingticketcinemabe.annotation.ValueOfEnumValidator;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValueOfEnumValidator.class)
public @interface ValueOfEnum {
    Class<? extends Enum<?>> enumClass();

    String message() default "must be any of enums";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

package org.cybersoft.bookingticketcinemabe.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.cybersoft.bookingticketcinemabe.annotation.validator.OnlyOneOf;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class OnlyOneOfValidator implements ConstraintValidator<OnlyOneOf, Object> {

    private String[] fields;
    private String defaultMessage;

    @Override
    public void initialize(OnlyOneOf constraintAnnotation) {
        this.fields = constraintAnnotation.fields();
        defaultMessage = "Only one of the following fields can be provided: " + String.join(", ", fields);
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        try {
            boolean isValid;
            Set<String> fieldNames = new HashSet<>(Set.of(fields));
            int count = 0;
            for (Field field : value.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (fieldNames.contains(field.getName()) && field.get(value) != null) {
                    count++;
                }
            }
            if (count <= 1) isValid = true;
            else {
                isValid = false;
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(defaultMessage)
                        .addConstraintViolation();
            }
            return isValid;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

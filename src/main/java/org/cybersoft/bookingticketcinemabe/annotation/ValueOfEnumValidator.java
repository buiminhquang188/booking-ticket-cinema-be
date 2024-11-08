package org.cybersoft.bookingticketcinemabe.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.cybersoft.bookingticketcinemabe.annotation.validator.ValueOfEnum;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, Object> {
    private List<String> acceptedValues;
    private String defaultMessage;

    @Override
    public void initialize(ValueOfEnum annotation) {
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
        defaultMessage = annotation.enumClass().getSimpleName() + " must be any of: " + String.join(", ", acceptedValues);
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        boolean isValid;
        
        if (value instanceof String) {
            isValid = acceptedValues.contains(value);
        } else if (value instanceof List<?>) {
            List<?> valueList = (List<?>) value;
            isValid = valueList.stream()
                    .allMatch(item -> item instanceof String && acceptedValues.contains(item));
        } else {
            isValid = false;
        }

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(defaultMessage)
                    .addConstraintViolation();
        }

        return isValid;
    }
}

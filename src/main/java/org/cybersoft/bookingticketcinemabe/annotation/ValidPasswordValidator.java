package org.cybersoft.bookingticketcinemabe.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.cybersoft.bookingticketcinemabe.annotation.validator.ValidPassword;

public class ValidPasswordValidator implements ConstraintValidator<ValidPassword, String> {

    private int minLength;
    private String specialChars;

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            setContextMessage(context, "Password is not null.");
            return false;
        }
        if (password.length() < minLength) {
            setContextMessage(context, "Password must be at least " + minLength + " characters long.");
            return false;
        }
        if (!password.matches(".*\\d.*")) {
            setContextMessage(context, "Password must have at least one number.");
            return false;
        }
        if (!password.matches(".*[A-Z].*")) {
            setContextMessage(context, "Password must have at least one uppercase character.");
            return false;
        }
        if (!password.matches(".*[a-z].*")) {
            setContextMessage(context, "Password must have at least one lowercase character.");
            return false;
        }
        if (!password.matches(".*[" + specialChars + "].*")) {
            setContextMessage(context, "Password must have at least one special character from: " + specialChars);
            return false;
        }
        return true;
    }

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
        this.minLength = constraintAnnotation.minLength();
        this.specialChars = constraintAnnotation.specialChars();
    }

    private void setContextMessage(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
    }
}

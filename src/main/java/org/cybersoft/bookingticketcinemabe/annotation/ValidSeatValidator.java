package org.cybersoft.bookingticketcinemabe.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.cybersoft.bookingticketcinemabe.annotation.validator.ValidSeat;
import org.cybersoft.bookingticketcinemabe.exception.runtime.NotValidException;
import org.cybersoft.bookingticketcinemabe.payload.request.seat.SeatUpdateRequest;

public class ValidSeatValidator implements ConstraintValidator<ValidSeat, SeatUpdateRequest> {
    @Override
    public boolean isValid(SeatUpdateRequest seatUpdateRequest, ConstraintValidatorContext constraintValidatorContext) {
        if (!seatUpdateRequest.isActive() && seatUpdateRequest.seatPrice() > 0) {
            throw new NotValidException(constraintValidatorContext.getDefaultConstraintMessageTemplate());
        }

        return true;
    }
}

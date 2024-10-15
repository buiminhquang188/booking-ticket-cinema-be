package org.cybersoft.bookingticketcinemabe.annotation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.cybersoft.bookingticketcinemabe.annotation.validator.ValidSeats;
import org.cybersoft.bookingticketcinemabe.exception.runtime.NotValidException;
import org.cybersoft.bookingticketcinemabe.payload.request.hall.HallUpdateSeat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ValidSeatsValidator implements ConstraintValidator<ValidSeats, List<HallUpdateSeat>> {
    @Override
    public boolean isValid(List<HallUpdateSeat> seats, ConstraintValidatorContext context) {
        if (seats == null) {
            return true;
        }

        Set<String> uniqueSeats = new HashSet<>();
        for (HallUpdateSeat seat : seats) {
            String seatIdentifier = seat.seatRow() + "-" + seat.seatColumn() + "-" + seat.seatNumber();
            if (!uniqueSeats.add(seatIdentifier)) {
                throw new NotValidException("Seat " + seatIdentifier + " is duplicated");
            }
        }
        return true;
    }
}
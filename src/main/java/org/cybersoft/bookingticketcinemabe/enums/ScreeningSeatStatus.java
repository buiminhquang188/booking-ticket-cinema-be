package org.cybersoft.bookingticketcinemabe.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ScreeningSeatStatus {
    EMPTY(false),
    BOOK(true);

    private final Boolean status;
}

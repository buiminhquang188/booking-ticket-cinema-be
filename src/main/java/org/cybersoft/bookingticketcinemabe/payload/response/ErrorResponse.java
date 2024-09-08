package org.cybersoft.bookingticketcinemabe.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class ErrorResponse<T> {
    private String statusCode;
    private String status;
    private ErrorDetailResponse<T> error;
}

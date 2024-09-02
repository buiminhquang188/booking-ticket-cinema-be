package org.cybersoft.bookingticketcinemabe.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class BaseResponse<T> {
    private int statusCode = 200;
    private String message;
    private T data;
}

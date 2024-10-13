package org.cybersoft.bookingticketcinemabe.query.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Page<T> {
    private T data;
    private Integer pageNumber;
    private Integer pageSize;
    private Integer totalPage;
    private Long totalElement;
}

package org.cybersoft.bookingticketcinemabe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageableDTO<T> {
    private T content;
    private Integer pageSize;
    private Integer pageNo;
    private Integer totalPages;
    private Long totalItems;
}

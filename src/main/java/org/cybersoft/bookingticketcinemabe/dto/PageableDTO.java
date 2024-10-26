package org.cybersoft.bookingticketcinemabe.dto;

import lombok.Data;

@Data
public class PageableDTO<T> {
    private T content;
    private Integer pageSize;
    private Integer pageNo;
    private Integer totalPages;
    private Long totalItems;
}

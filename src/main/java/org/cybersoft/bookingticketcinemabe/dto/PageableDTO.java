package org.cybersoft.bookingticketcinemabe.dto;

import lombok.Data;

@Data
public class PageableDTO<T> {
    private T content;
    private int pageSize;
    private int pageNo;
    private int totalPages;
    private long totalItems;
}

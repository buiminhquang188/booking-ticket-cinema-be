package org.cybersoft.bookingticketcinemabe.mapper;

import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.springframework.data.domain.Page;

public class PageableMapper<T> {
    public PageableDTO<T> toDTO(Page<?> page) {
        PageableDTO<T> pageableDTO = new PageableDTO<>();
        pageableDTO.setContent((T) page.getContent());
        pageableDTO.setPageSize(page.getSize());
        pageableDTO.setPageNo(page.getNumber() + 1);
        pageableDTO.setTotal(page.getTotalPages());
        return pageableDTO;
    }
}


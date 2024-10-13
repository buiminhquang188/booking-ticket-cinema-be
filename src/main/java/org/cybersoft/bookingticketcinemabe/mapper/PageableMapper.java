package org.cybersoft.bookingticketcinemabe.mapper;

import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public class PageableMapper<T> {
    public PageableDTO<T> toDTO(Page<?> page) {
        PageableDTO<T> pageableDTO = new PageableDTO<>();
        pageableDTO.setContent((T) page.getContent());
        pageableDTO.setPageSize(page.getPageable()
                .getPageSize());
        pageableDTO.setPageNo(page.getPageable()
                .getPageNumber());
        pageableDTO.setTotalPages(page.getTotalPages());
        pageableDTO.setTotalItems(page.getTotalElements());
        return pageableDTO;
    }

    public <R> PageableDTO<List<R>> toDTO(org.cybersoft.bookingticketcinemabe.query.dto.Page<List<R>> page) {
        PageableDTO<List<R>> pageableDTO = new PageableDTO<>();
        pageableDTO.setContent(page.getData());
        pageableDTO.setPageSize(page.getPageSize());
        pageableDTO.setPageNo(page.getPageNumber());
        pageableDTO.setTotalPages(page.getTotalPage());
        pageableDTO.setTotalItems(page.getTotalElement());
        return pageableDTO;
    }
}


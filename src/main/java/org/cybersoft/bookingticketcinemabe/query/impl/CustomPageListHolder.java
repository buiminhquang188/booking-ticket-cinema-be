package org.cybersoft.bookingticketcinemabe.query.impl;

import lombok.Getter;
import lombok.Setter;
import org.cybersoft.bookingticketcinemabe.query.IPageListHolder;
import org.cybersoft.bookingticketcinemabe.query.dto.Page;
import org.cybersoft.bookingticketcinemabe.query.dto.Pageable;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.beans.support.SortDefinition;

import java.util.List;
import java.util.function.Function;

@Getter
@Setter
public class CustomPageListHolder<T> extends PagedListHolder<T> implements IPageListHolder<T> {
    private Long count;
    private Integer totalPages;
    private Pageable pageable;

    public CustomPageListHolder(List<T> source, SortDefinition sort, Long count, Pageable pageable) {
        super(source, sort);
        this.count = count;
        this.pageable = pageable;
        

        this.setPage(pageable.getPageNumber());
        this.setPageSize(pageable.getPageSize());
        this.setTotalPages((int) Math.ceil((double) count / this.getPageSize()));
    }

    @Override
    public <U> Page<List<U>> map(Function<? super T, U> converter) {
        List<U> data = this.getPageList()
                .stream()
                .map(converter)
                .toList();

        return Page.<List<U>>builder()
                .data(data)
                .pageSize(this.getPageSize())
                .pageNumber(this.pageable.getPageNumber())
                .totalPage(this.totalPages)
                .totalElement(this.count)
                .build();
    }
}

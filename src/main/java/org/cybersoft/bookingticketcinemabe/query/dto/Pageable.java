package org.cybersoft.bookingticketcinemabe.query.dto;

import lombok.Builder;
import lombok.Data;
import org.cybersoft.bookingticketcinemabe.query.enums.Order;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.SortDefinition;

@Data
@Builder
public class Pageable {
    private Integer pageNumber;
    private Integer pageSize;
    private SortDefinition sortDefinition;

    public static SortDefinition sort(String property, Order order) {
        boolean direction = order == Order.ASC;
        return new MutableSortDefinition(property, true, direction);
    }
}

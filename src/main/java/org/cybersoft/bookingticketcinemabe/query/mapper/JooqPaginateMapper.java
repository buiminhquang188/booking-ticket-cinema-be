package org.cybersoft.bookingticketcinemabe.query.mapper;

import org.cybersoft.bookingticketcinemabe.payload.request.minimal.MinimalCriteria;
import org.cybersoft.bookingticketcinemabe.query.dto.JooqPaginate;
import org.jooq.Result;
import org.springframework.stereotype.Component;

@Component
public class JooqPaginateMapper {
    public JooqPaginate toPaginate(Result<?> result, MinimalCriteria criteria) {
        return JooqPaginate.builder()
                .pageNumber(this.createPageNumber(result, criteria.getPageNo()))
                .pageSize(this.createPageSize(result, criteria.getPageLimit()))
                .totalPage(this.createTotalPage(result, criteria.getPageLimit()))
                .totalElement(this.createTotalElements(result))
                .build();
    }

    private Integer createPageNumber(Result<?> result, Integer pageNo) {
        if (result.isEmpty()) {
            return pageNo;
        }
        return result.getFirst()
                .get("row", Integer.class);
    }

    private Integer createPageSize(Result<?> result, Integer pageLimit) {
        if (result.isEmpty()) {
            return pageLimit;
        }
        return result.getFirst()
                .get("actual_page_size", Integer.class);
    }

    private Long createTotalElements(Result<?> result) {
        if (result.isEmpty()) {
            return 0L;
        }
        return result.getFirst()
                .get("total_rows", Long.class);
    }

    private Integer createTotalPage(Result<?> result, Integer pageLimit) {
        if (result.isEmpty()) {
            return 0;
        }
        return (int) Math.ceil(result.getFirst()
                                       .get("total_rows", Long.class) / pageLimit) + 1;
    }
}

package org.cybersoft.bookingticketcinemabe.query;


import org.cybersoft.bookingticketcinemabe.query.dto.Page;

import java.util.List;
import java.util.function.Function;

public interface IPageListHolder<T> {
    <U> Page<List<U>> map(Function<? super T, U> converter);
}

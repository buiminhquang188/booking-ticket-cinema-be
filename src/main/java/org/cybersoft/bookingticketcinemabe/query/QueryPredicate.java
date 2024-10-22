package org.cybersoft.bookingticketcinemabe.query;

import jakarta.persistence.criteria.CommonAbstractCriteria;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;


@FunctionalInterface
public interface QueryPredicate<R> {
    Predicate apply(CommonAbstractCriteria criteria, CriteriaBuilder cb, Path<R> root);
}

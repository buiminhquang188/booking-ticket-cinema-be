package org.cybersoft.bookingticketcinemabe.query.impl;

import jakarta.persistence.criteria.CommonAbstractCriteria;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.metamodel.SingularAttribute;
import org.cybersoft.bookingticketcinemabe.query.BaseQuery;
import org.cybersoft.bookingticketcinemabe.query.QueryPredicate;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseQueryImpl<R, Q extends BaseQueryImpl<R, Q>> implements BaseQuery<R, Q> {
    protected final Collection<QueryPredicate<R>> predicates;

    public BaseQueryImpl() {
        this.predicates = new ArrayList<>();
    }

    protected abstract Q self();

    protected <T> Predicate[] buildPredicates(CommonAbstractCriteria criteria, Collection<QueryPredicate<T>> predicates, CriteriaBuilder cb, Path<T> root) {
        return predicates.stream()
                .map(t -> t.apply(criteria, cb, root))
                .toArray(Predicate[]::new);
    }

    @Override
    public <V> Q equal(SingularAttribute<R, V> attribute, V value) {
        predicates.add((criteria, cb, root) -> cb.equal(root.get(attribute), value));
        return self();
    }

    @Override
    public <P, V> Q equal(SingularAttribute<R, P> attribute1, SingularAttribute<P, V> attribute2, V value) {
        predicates.add((criteria, cb, root) -> cb.equal(root.get(attribute1)
                .get(attribute2), value));
        return self();
    }

    @Override
    public <P1, P2, V> Q equal(SingularAttribute<R, P1> attribute1, SingularAttribute<P1, P2> attribute2, SingularAttribute<P2, V> attribute3, V value) {
        predicates.add((criteria, cb, root) -> cb.equal(root.get(attribute1)
                .get(attribute2)
                .get(attribute3), value));
        return self();
    }
}

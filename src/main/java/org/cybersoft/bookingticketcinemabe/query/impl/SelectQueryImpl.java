package org.cybersoft.bookingticketcinemabe.query.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.persistence.metamodel.SingularAttribute;
import org.cybersoft.bookingticketcinemabe.query.SelectQuery;
import org.cybersoft.bookingticketcinemabe.query.utils.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SelectQueryImpl<R> extends BaseQueryImpl<R, SelectQueryImpl<R>> implements SelectQuery<R, SelectQueryImpl<R>> {
    private final EntityManager em;
    private final CriteriaBuilder cb;
    private final CriteriaQuery query;
    private final Root<R> root;
    private final List<jakarta.persistence.criteria.Order> orderList;

    public SelectQueryImpl(EntityManager em, Class<R> type) {
        this.em = em;
        this.cb = em.getCriteriaBuilder();
        this.query = cb.createQuery();
        this.root = query.from(type);
        this.orderList = new ArrayList<>();
    }

    @Override
    protected SelectQueryImpl<R> self() {
        return this;
    }

    private <T> TypedQuery<T> buildQuery(Selection<T> selection) {
        CriteriaQuery<T> resultQuery = query
                .select(selection)
                .orderBy(orderList);
        if (!predicates.isEmpty()) {
            resultQuery = resultQuery.where(buildPredicates(query, predicates, cb, root));
        }
        return em.createQuery(resultQuery);
    }

    @Override
    public List<R> findAll() {
        return buildQuery(root)
                .getResultList();
    }

    @Override
    public <P> List<P> findAll(SingularAttribute<R, P> attribute) {
        return buildQuery(root.get(attribute))
                .getResultList();
    }

    @Override
    public <P1, P2> List<P2> findAll(SingularAttribute<R, P1> attribute1, SingularAttribute<P1, P2> attribute2) {
        return buildQuery(root.get(attribute1)
                .get(attribute2))
                .getResultList();
    }

    @Override
    public R getOne() {
        return buildQuery(root)
                .setMaxResults(1)
                .getSingleResult();
    }

    @Override
    public <P> P getOne(SingularAttribute<R, P> attribute) {
        return buildQuery(root.get(attribute))
                .setMaxResults(1)
                .getSingleResult();
    }

    @Override
    public <P1, P2> P2 getOne(SingularAttribute<R, P1> attribute1, SingularAttribute<P1, P2> attribute2) {
        return buildQuery(root.get(attribute1)
                .get(attribute2))
                .setMaxResults(1)
                .getSingleResult();
    }

    @Override
    public Optional<R> findOne() {
        try {
            return Optional.of(getOne());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public <P> Optional<P> findOne(SingularAttribute<R, P> attribute) {
        try {
            return Optional.of(getOne(attribute));
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public <P1, P2> Optional<P2> findOne(SingularAttribute<R, P1> attribute1, SingularAttribute<P1, P2> attribute2) {
        try {
            return Optional.of(getOne(attribute1, attribute2));
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public long count() {
        return buildQuery(cb.count(root))
                .getSingleResult();
    }

    @Override
    public <P> long count(SingularAttribute<R, P> attribute) {
        return buildQuery(cb.count(root.get(attribute)))
                .getSingleResult();
    }

    @Override
    public <P1, P2> long count(SingularAttribute<R, P1> attribute1, SingularAttribute<P1, P2> attribute2) {
        return buildQuery(cb.count(root.get(attribute1)
                .get(attribute2)))
                .getSingleResult();
    }

    @Override
    public <P> SelectQueryImpl<R> order(SingularAttribute<R, P> attribute) {
        return order(attribute, Order.ASC);
    }

    @Override
    public <P> SelectQueryImpl<R> order(SingularAttribute<R, P> attribute, Order sort) {
        jakarta.persistence.criteria.Order order = sort == Order.ASC ? cb.asc(root.get(attribute)) : cb.desc(root.get(attribute));
        orderList.add(order);
        return this;
    }

    @Override
    public <P1, P2> SelectQueryImpl<R> order(SingularAttribute<R, P1> attribute1,
                                             SingularAttribute<P1, P2> attribute2) {
        return order(attribute1, attribute2, Order.ASC);
    }

    @Override
    public <P1, P2> SelectQueryImpl<R> order(SingularAttribute<R, P1> attribute1,
                                             SingularAttribute<P1, P2> attribute2, Order sort) {
        Path<P2> path = root.get(attribute1)
                .get(attribute2);
        jakarta.persistence.criteria.Order order = sort == Order.ASC ? cb.asc(path) : cb.desc(path);
        orderList.add(order);
        return this;
    }

    @Override
    public <P1, P2, P3> SelectQueryImpl<R> order(SingularAttribute<R, P1> attribute1,
                                                 SingularAttribute<P1, P2> attribute2,
                                                 SingularAttribute<P2, P3> attribute3) {
        return order(attribute1, attribute2, attribute3, Order.ASC);
    }

    @Override
    public <P1, P2, P3> SelectQueryImpl<R> order(SingularAttribute<R, P1> attribute1,
                                                 SingularAttribute<P1, P2> attribute2,
                                                 SingularAttribute<P2, P3> attribute3, Order sort) {
        Path<P3> path = root.get(attribute1)
                .get(attribute2)
                .get(attribute3);
        jakarta.persistence.criteria.Order order = sort == Order.ASC ? cb.asc(path) : cb.desc(path);
        orderList.add(order);
        return this;
    }

    @Override
    public <P> SelectQueryImpl<R> fetch(SingularAttribute<R, P> attribute) {
        root.fetch(attribute);
        return this;
    }

    @Override
    public <P1, P2> SelectQueryImpl<R> fetch(SingularAttribute<R, P1> attribute1, SingularAttribute<P1, P2> attribute2) {
        root.fetch(attribute1)
                .fetch(attribute2);
        return this;
    }

    @Override
    public <P1, P2, P3> SelectQueryImpl<R> fetch(SingularAttribute<R, P1> attribute1, SingularAttribute<P1, P2> attribute2,
                                                 SingularAttribute<P2, P3> attribute3) {
        root.fetch(attribute1)
                .fetch(attribute2)
                .fetch(attribute3);
        return this;
    }
}

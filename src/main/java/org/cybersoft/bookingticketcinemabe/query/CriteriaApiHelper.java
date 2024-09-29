package org.cybersoft.bookingticketcinemabe.query;

import jakarta.persistence.EntityManager;
import org.cybersoft.bookingticketcinemabe.query.impl.SelectQueryImpl;

public class CriteriaApiHelper {
    private final EntityManager em;

    public CriteriaApiHelper(EntityManager em) {
        this.em = em;
    }

    public <T> SelectQueryImpl<T> select(Class<T> type) {
        return new SelectQueryImpl<>(em, type);
    }

}

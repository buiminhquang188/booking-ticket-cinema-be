package org.cybersoft.bookingticketcinemabe.query.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.Metamodel;
import org.cybersoft.bookingticketcinemabe.exception.runtime.NotFoundColumnException;


public class Helpers {
    public static <R> void isValidColumn(EntityManager entityManager, Path<? extends R> root, String columnName) {
        Metamodel metamodel = entityManager.getMetamodel();
        EntityType<R> entityType = (EntityType<R>) metamodel.entity(root.getJavaType());

        if (!entityType.getSingularAttributes()
                .stream()
                .anyMatch(attr -> attr.getName()
                        .equals(columnName))) {
            throw new NotFoundColumnException("Column " + columnName + " not found");
        }
    }
}

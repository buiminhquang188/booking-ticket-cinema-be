package org.cybersoft.bookingticketcinemabe.query.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.Metamodel;
import org.cybersoft.bookingticketcinemabe.exception.runtime.NotFoundColumnException;
import org.jooq.*;

import static org.jooq.impl.DSL.*;


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

    public static Result<?> paginate(
            DSLContext ctx,
            Select<?> original,
            Field<?>[] sort,
            int limit,
            int offset
    ) {
        Table<?> u = original.asTable("u");
        Field<Integer> totalRows = count().over()
                .as("total_rows");
        Field<Integer> row = rowNumber().over()
                .orderBy(u.fields(sort))
                .as("row");


        Table<?> t = ctx
                .select(u.asterisk())
                .select(totalRows, row)
                .from(u)
                .orderBy(u.fields(sort))
                .limit(limit)
                .offset(offset)
                .asTable("t");

        return ctx
                .select(t.fields(original.getSelect()
                        .toArray(Field[]::new)))
                .select(
                        count().over()
                                .as("actual_page_size"),
                        field(max(t.field(row)).over()
                                .eq(t.field(totalRows)))
                                .as("last_page"),
                        t.field(totalRows),
                        t.field(row),
                        t.field(row)
                                .minus(inline(1))
                                .div(limit)
                                .plus(inline(1))
                                .as("current_page"))
                .from(t)
                .orderBy(t.fields(sort))
                .fetch();
    }
}

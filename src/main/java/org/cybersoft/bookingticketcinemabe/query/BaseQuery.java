package org.cybersoft.bookingticketcinemabe.query;

import jakarta.persistence.criteria.ListJoin;
import jakarta.persistence.metamodel.SingularAttribute;
import org.cybersoft.bookingticketcinemabe.query.impl.QueryPart;

import java.time.LocalDateTime;
import java.util.Collection;

public interface BaseQuery<R, Q extends BaseQuery<R, Q>> {
    <V> Q equal(SingularAttribute<R, V> attribute, V value);

    <P, V> Q equal(SingularAttribute<R, P> attribute1, SingularAttribute<P, V> attribute2, V value);

    <P1, P2, V> Q equal(SingularAttribute<R, P1> attribute1, SingularAttribute<P1, P2> attribute2, SingularAttribute<P2, V> attribute3, V value);

    <P1, P2, P3, V> Q equal(SingularAttribute<R, P1> attribute1, SingularAttribute<P1, P2> attribute2, SingularAttribute<P2, P3> attribute3, SingularAttribute<P3, V> attribute4, V value);

    <J, V> Q equal(SingularAttribute<J, V> attribute, V value, ListJoin<R, J> root);

    <V> Q notEqual(SingularAttribute<R, V> attribute, V value);

    <P, V> Q notEqual(SingularAttribute<R, P> attribute1, SingularAttribute<P, V> attribute2, V value);

    <P1, P2, V> Q notEqual(SingularAttribute<R, P1> attribute1, SingularAttribute<P1, P2> attribute2, SingularAttribute<P2, V> attribute3, V value);

    <P1, P2, P3, V> Q notEqual(SingularAttribute<R, P1> attribute1, SingularAttribute<P1, P2> attribute2, SingularAttribute<P2, P3> attribute3, SingularAttribute<P3, V> attribute4, V value);

    <J, V> Q notEqual(SingularAttribute<J, V> attribute, V value, ListJoin<R, J> root);

    Q isTrue(SingularAttribute<R, Boolean> attribute);

    <P> Q isTrue(SingularAttribute<R, P> attribute1, SingularAttribute<P, Boolean> attribute2);

    <P1, P2> Q isTrue(SingularAttribute<R, P1> attribute1, SingularAttribute<P1, P2> attribute2, SingularAttribute<P2, Boolean> attribute3);

    <P1, P2, P3> Q isTrue(SingularAttribute<R, P1> attribute1, SingularAttribute<P1, P2> attribute2, SingularAttribute<P2, P3> attribute3, SingularAttribute<P3, Boolean> attribute4);

    <J> Q isTrue(SingularAttribute<J, Boolean> attribute, ListJoin<R, J> root);

    Q isFalse(SingularAttribute<R, Boolean> attribute);

    <P> Q isFalse(SingularAttribute<R, P> attribute1, SingularAttribute<P, Boolean> attribute2);

    <P1, P2> Q isFalse(SingularAttribute<R, P1> attribute1, SingularAttribute<P1, P2> attribute2, SingularAttribute<P2, Boolean> attribute3);

    <P1, P2, P3> Q isFalse(SingularAttribute<R, P1> attribute1, SingularAttribute<P1, P2> attribute2, SingularAttribute<P2, P3> attribute3, SingularAttribute<P3, Boolean> attribute4);

    <J> Q isFalse(SingularAttribute<J, Boolean> attribute, ListJoin<R, J> root);

    <V> Q isNull(SingularAttribute<R, V> attribute);

    <P, V> Q isNull(SingularAttribute<R, P> attribute1, SingularAttribute<P, V> attribute2);

    <P1, P2, V> Q isNull(SingularAttribute<R, P1> attribute1, SingularAttribute<P1, P2> attribute2, SingularAttribute<P2, V> attribute3);

    <P1, P2, P3, V> Q isNull(SingularAttribute<R, P1> attribute1, SingularAttribute<P1, P2> attribute2, SingularAttribute<P2, P3> attribute3, SingularAttribute<P3, V> attribute4);

    <J, V> Q isNull(SingularAttribute<J, V> attribute, ListJoin<R, J> root);

    <V> Q isNotNull(SingularAttribute<R, V> attribute);

    <P, V> Q isNotNull(SingularAttribute<R, P> attribute1, SingularAttribute<P, V> attribute2);

    <P1, P2, V> Q isNotNull(SingularAttribute<R, P1> attribute1, SingularAttribute<P1, P2> attribute2, SingularAttribute<P2, V> attribute3);

    <P1, P2, P3, V> Q isNotNull(SingularAttribute<R, P1> attribute1, SingularAttribute<P1, P2> attribute2, SingularAttribute<P2, P3> attribute3, SingularAttribute<P3, V> attribute4);

    <J, V> Q isNotNull(SingularAttribute<J, V> attribute, ListJoin<R, J> root);

    <V extends Comparable<? super V>> Q greaterThan(SingularAttribute<R, V> attribute, V value);

    <P, V extends Comparable<? super V>> Q greaterThan(SingularAttribute<R, P> attribute1, SingularAttribute<P, V> attribute2, V value);

    <P1, P2, V extends Comparable<? super V>> Q greaterThan(SingularAttribute<R, P1> attribute1, SingularAttribute<P1, P2> attribute2, SingularAttribute<P2, V> attribute3, V value);

    <P1, P2, P3, V extends Comparable<? super V>> Q greaterThan(SingularAttribute<R, P1> attribute1, SingularAttribute<P1, P2> attribute2, SingularAttribute<P2, P3> attribute3, SingularAttribute<P3, V> attribute4, V value);

    <J, V extends Comparable<? super V>> Q greaterThan(SingularAttribute<J, V> attribute, V value, ListJoin<R, J> root);

    <V extends Comparable<? super V>> Q lessThan(SingularAttribute<R, V> attribute, V value);

    <P, V extends Comparable<? super V>> Q lessThan(SingularAttribute<R, P> attribute1, SingularAttribute<P, V> attribute2, V value);

    <P1, P2, V extends Comparable<? super V>> Q lessThan(SingularAttribute<R, P1> attribute1, SingularAttribute<P1, P2> attribute2, SingularAttribute<P2, V> attribute3, V value);

    <P1, P2, P3, V extends Comparable<? super V>> Q lessThan(SingularAttribute<R, P1> attribute1, SingularAttribute<P1, P2> attribute2, SingularAttribute<P2, P3> attribute3, SingularAttribute<P3, V> attribute4, V value);

    <J, V extends Comparable<? super V>> Q lessThan(SingularAttribute<J, V> attribute, V value, ListJoin<R, J> root);

    Q like(SingularAttribute<R, String> attribute, String value);

    <P> Q like(SingularAttribute<R, P> attribute1, SingularAttribute<P, String> attribute2, String value);

    <P1, P2> Q like(SingularAttribute<R, P1> attribute1, SingularAttribute<P1, P2> attribute2, SingularAttribute<P2, String> attribute3, String value);

    <P1, P2, P3> Q like(SingularAttribute<R, P1> attribute1, SingularAttribute<P1, P2> attribute2, SingularAttribute<P2, P3> attribute3, SingularAttribute<P3, String> attribute4, String value);

    <J> Q like(SingularAttribute<J, String> attribute, String value, ListJoin<R, J> root);

    Q and(QueryPart<R>... partQueries);

    <P> Q and(SingularAttribute<R, P> attribute, QueryPart<P>... partQueries);

    <P1, P2> Q and(SingularAttribute<R, P1> attribute1, SingularAttribute<P1, P2> attribute2, QueryPart<P2>... partQueries);

    <P1, P2, P3> Q and(SingularAttribute<R, P1> attribute1, SingularAttribute<P1, P2> attribute2, SingularAttribute<P2, P3> attribute3, QueryPart<P3>... partQueries);

    <J> Q and(ListJoin<R, J> root, QueryPart<J>... partQueries);

    Q or(QueryPart<R>... partQueries);

    <P> Q or(SingularAttribute<R, P> attribute, QueryPart<P>... partQueries);

    <P1, P2> Q or(SingularAttribute<R, P1> attribute1, SingularAttribute<P1, P2> attribute2, QueryPart<P2>... partQueries);

    <P1, P2, P3> Q or(SingularAttribute<R, P1> attribute1, SingularAttribute<P1, P2> attribute2, SingularAttribute<P2, P3> attribute3, QueryPart<P3>... partQueries);

    <J> Q or(ListJoin<R, J> root, QueryPart<J>... partQueries);

    <V> Q in(SingularAttribute<R, V> attribute, Collection<V> values);

    <P, V> Q in(SingularAttribute<R, P> attribute1, SingularAttribute<P, V> attribute2, Collection<V> values);

    <P1, P2, V> Q in(SingularAttribute<R, P1> attribute1, SingularAttribute<P1, P2> attribute2, SingularAttribute<P2, V> attribute3, Collection<V> values);

    <J, V> Q in(SingularAttribute<J, V> attribute, Collection<V> values, ListJoin<R, J> root);

    Q between(String attribute, LocalDateTime from, LocalDateTime to);

}

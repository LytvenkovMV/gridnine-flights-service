package com.gridnine.testing.filter;

import com.gridnine.testing.predicate.AbstractPredicate;

import java.util.List;

public interface Filter<T> {
    Filter<T> doFilter(AbstractPredicate<T> predicate);

    List<T> getFiltered();
}
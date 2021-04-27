package io.akshay.partyinvitation.providers;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@FunctionalInterface
public interface Filter<T> extends Predicate<T> {

    default List<T> bulk(List<T> items) {
        return items.stream().filter(this).collect(Collectors.toList());
    }
}

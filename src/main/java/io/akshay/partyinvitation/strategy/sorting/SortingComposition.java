package io.akshay.partyinvitation.strategy.sorting;

import io.akshay.partyinvitation.models.Customer;

import java.util.*;
import java.util.stream.Stream;

/**
 * Composite chain of comparator(s) to be applied one after another
 */
public class SortingComposition implements Comparator<Customer> {

    private Comparator<Customer> composed;

    private SortingComposition(Comparator<Customer> composed) {
        this.composed = composed;
    }

    @SafeVarargs
    public static SortingComposition with(Comparator<Customer>... comparators) {
        Comparator<Customer> composed = Stream.of(comparators).reduce(egalitarian(), Comparator::thenComparing);
        return new SortingComposition(composed);
    }

    @Override
    public int compare(Customer one, Customer two) {
        return composed.compare(one, two);
    }

    /**
     * Does no comparison. Returns 0 (equal) for all customers
     *
     * @return Return 0 (all objects are equal)
     */
    private static Comparator<Customer> egalitarian() {
        return (c1, c2) -> 0;
    }
}

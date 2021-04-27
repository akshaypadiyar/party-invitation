package io.akshay.partyinvitation.strategy.sorting;

import io.akshay.partyinvitation.models.Customer;

import java.util.Comparator;
import java.util.Objects;

/**
 * Sorting based on customer name
 */
public final class CustomerNameSorting implements Comparator<Customer> {

    /**
     * Compares two customer based on their name
     * @param one {@link Customer} First customer
     * @param two {@link Customer} Second customer
     * @return A negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second
     */
    @Override
    public int compare(Customer one, Customer two) {
        return Objects.compare(one, two, Comparator.comparing(Customer::getName));
    }
}

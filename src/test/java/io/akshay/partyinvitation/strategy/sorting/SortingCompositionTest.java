package io.akshay.partyinvitation.strategy.sorting;

import io.akshay.partyinvitation.TestUtils;
import io.akshay.partyinvitation.models.Customer;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

class SortingCompositionTest {

    private SortingComposition chain;

    private Comparator<Customer> sortByName = new CustomerNameSorting();

    private Comparator<Customer> sortById = Comparator.comparingInt(Customer::getId);

    @Test
    void testChainingOfSortStrategies() {
        chain = SortingComposition.with(sortByName, sortById);
        Customer one = TestUtils.createCustomer(10, "Apple", -1, 1);
        Customer two = TestUtils.createCustomer(2, "Apple", -1, 1);

        assertEquals(1, chain.compare(one, two), "Sorting Chain failed");
    }
}
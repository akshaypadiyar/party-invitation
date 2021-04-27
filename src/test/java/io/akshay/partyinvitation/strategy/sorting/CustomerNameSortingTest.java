package io.akshay.partyinvitation.strategy.sorting;

import io.akshay.partyinvitation.TestUtils;
import io.akshay.partyinvitation.models.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class CustomerNameSortingTest {

    private Comparator<Customer> comparator;

    @BeforeEach
    void setup() {
        comparator = new CustomerNameSorting();
    }


    @Test
    void testCustomerNameSorting() {
        Customer one = TestUtils.createCustomer(10, "Apple", -1, 1);
        Customer two = TestUtils.createCustomer(2, "Banana", -1, 1);

        assertAll(
                () -> assertEquals(-1, comparator.compare(one, two)),
                () -> assertEquals(1, comparator.compare(two, one)));
    }

    @Test
    void testWhenCustomerNameIsSame() {
        Customer one = TestUtils.createCustomer(10, "Tom Hanks", -1, 1);
        Customer two = TestUtils.createCustomer(2, "Tom Hanks", -1, 1);
        assertEquals(0, comparator.compare(one, two));
    }
}
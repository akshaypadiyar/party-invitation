package io.akshay.partyinvitation.providers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FilterTest {

    Filter<Integer> filter;

    @BeforeEach
    public void setup() {
        filter = num -> num % 2 == 0;
    }

    @Test
    public void testSingleFilter() {
        assertTrue(filter.test(100));
    }

    @Test
    public void testSingleFilterFalse() {
        assertFalse(filter.test(101));
    }

    @Test
    public void testBulkFilterEmpty() {
        assertEquals(0, filter.bulk(List.of(1, 3, 5, 7, 9, 11)).size());
    }

    @Test
    public void testBulkFilter() {
        var expected = List.of(2, 4, 6, 8, 10);
        assertIterableEquals(expected, filter.bulk(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)));
    }
}
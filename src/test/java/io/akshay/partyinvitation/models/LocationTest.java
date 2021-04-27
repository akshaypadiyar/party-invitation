package io.akshay.partyinvitation.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocationTest {

    Location test;

    @Test
    void getLatitude() {
        test = new Location(1, -1);
        assertEquals(1, test.getLatitude());
    }

    @Test
    void setLatitude() {
        test = new Location(1, -1);
        test.setLatitude(2.001);
        assertEquals(2.001, test.getLatitude());
    }

    @Test
    void getLongitude() {
        test = new Location(1, -1);
        assertEquals(-1, test.getLongitude());
    }

    @Test
    void setLongitude() {
        test = new Location(1, -1);
        test.setLongitude(-2.22);
        assertEquals(-2.22, test.getLongitude());
    }
}
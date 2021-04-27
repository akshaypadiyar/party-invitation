package io.akshay.partyinvitation.services.location;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DistanceConvertersTest {

    private final Double epsilon = 0.000001d;

    @Test
    void testNoConversion() {
        DistanceConverter converter = DistanceConverters.nop();
        Double distance = 10.12454;
        assertEquals(distance, converter.convert(distance));
    }

    @Test
    void testKmsToMilesConversion() {
        DistanceConverter converter = DistanceConverters.milesToKms();
        Double expectedKms = 1.609344;
        Double actualKms = converter.convert(1D);
        assertTrue(Math.abs(expectedKms - actualKms) < epsilon);
    }

    @Test
    void testMilesToKmsConversion() {
        DistanceConverter converter = DistanceConverters.kmsToMiles();
        Double expectedMiles = 0.621371;
        Double actualMiles = converter.convert(1D);
        assertTrue(Math.abs(expectedMiles - actualMiles) < epsilon);
    }
}
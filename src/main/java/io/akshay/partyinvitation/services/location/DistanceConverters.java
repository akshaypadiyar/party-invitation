package io.akshay.partyinvitation.services.location;

public class DistanceConverters {

    private static final double KM_TO_MILE_RATIO = 0.621371;

    /**
     * Performs no conversion
     *
     * @return {@link DistanceConverter} That performs no conversion
     */
    public static DistanceConverter nop() {
        return value -> value.isNaN() ? 0 : value;
    }

    /**
     * Converts Kilometers to Miles
     *
     * @return {@link DistanceConverter}
     */
    public static DistanceConverter kmsToMiles() {
        return value -> value.isNaN() ? 0 : value * KM_TO_MILE_RATIO;
    }

    /**
     * Converts Miles to Kilometers
     *
     * @return {@link DistanceConverter}
     */
    public static DistanceConverter milesToKms() {
        return value -> value.isNaN() ? 0 : value / KM_TO_MILE_RATIO;
    }
}

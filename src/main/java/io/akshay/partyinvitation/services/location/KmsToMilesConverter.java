package io.akshay.partyinvitation.services.location;

import org.springframework.core.convert.converter.Converter;

/**
 * Converts Kms to miles
 */
public class KmsToMilesConverter implements Converter<Double, Double> {

    private final double KM_TO_MILE_RATIO = 0.621371;

    @Override
    public Double convert(Double aDouble) {
        return aDouble * KM_TO_MILE_RATIO;
    }
}

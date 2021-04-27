package io.akshay.partyinvitation.services.location;

import org.springframework.core.convert.converter.Converter;

/**
 * Default.. Performs no conversion..
 */
public class NoConversion implements Converter<Double, Double> {

    @Override
    public Double convert(Double value) {
        return value;
    }
}

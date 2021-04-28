package io.akshay.partyinvitation.services.location;

import org.springframework.core.convert.converter.Converter;

/**
 * Converts distance between units
 */
@FunctionalInterface
public interface DistanceConverter extends Converter<Double, Double> {
}

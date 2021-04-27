package io.akshay.partyinvitation.services.location;

import org.springframework.core.convert.converter.Converter;

@FunctionalInterface
public interface DistanceConverter extends Converter<Double, Double> {
}

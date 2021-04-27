package io.akshay.partyinvitation.config;

import io.akshay.partyinvitation.services.location.DistanceConverter;
import io.akshay.partyinvitation.services.location.DistanceConverters;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilteringConfiguration {

    @Bean
    @ConditionalOnProperty(value = "application.measurement.units", havingValue = "miles", matchIfMissing = false)
    public DistanceConverter milesConverter() {
        return DistanceConverters.kmsToMiles();
    }

    @Bean
    @ConditionalOnMissingBean
    public DistanceConverter kmsConverter() {
        return DistanceConverters.nop();
    }
}

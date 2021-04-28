package io.akshay.partyinvitation.config;

import io.akshay.partyinvitation.models.Customer;
import io.akshay.partyinvitation.services.location.DistanceConverter;
import io.akshay.partyinvitation.services.location.DistanceConverters;
import io.akshay.partyinvitation.services.location.EventLocationProvider;
import io.akshay.partyinvitation.services.location.LocationService;
import io.akshay.partyinvitation.strategy.sorting.CustomerNameSorting;
import io.akshay.partyinvitation.strategy.sorting.CustomerProximitySorting;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import java.util.Comparator;

@Configuration
public class SortingConfiguration {

    @Value("${customers.sorting.reversed:false}")
    private boolean reversed;

    private Comparator<Customer> applyMode(Comparator<Customer> comparator) {
        return reversed ? comparator.reversed() : comparator;
    }

    /**
     * Sorting logic based on customer ID
     *
     * @return {@link Comparator}
     */
    @Bean
    @ConditionalOnProperty(value = "customers.sorting.strategy", havingValue = "user-id", matchIfMissing = true)
    public Comparator<Customer> customerIdSorting() {
        return applyMode(Comparator.comparing(Customer::getId));
    }

    /**
     * Customer name based sorting strategy
     *
     * @return {@link Comparator} based on {@link Customer} name.
     */
    @Bean
    @ConditionalOnProperty(value = "customers.sorting.strategy", havingValue = "name")
    public Comparator<Customer> customerNameSorting() {
        return applyMode(new CustomerNameSorting());
    }

    /**
     * Customer sorting strategy based on proximity from event location
     *
     * @param eventLocationProvider Provider of event location
     * @param locationService       Location Service to calculate distance
     * @return {@link Comparator}
     */
    @Bean
    @ConditionalOnProperty(value = "customers.sorting.strategy", havingValue = "proximity")
    public Comparator<Customer> proximitySorting(EventLocationProvider eventLocationProvider, LocationService locationService) {
        return applyMode(new CustomerProximitySorting(eventLocationProvider, locationService));
    }


    /**
     * Composite Sorting strategy.. Chains multiple strategies to derive desired behavior
     *
     * @return {@link Comparator}
     */
    @Bean
    @ConditionalOnMissingBean
    public Comparator<Customer> compositeSortingStrategy() {
        return applyMode(customerNameSorting().thenComparing(customerIdSorting()));
    }
}

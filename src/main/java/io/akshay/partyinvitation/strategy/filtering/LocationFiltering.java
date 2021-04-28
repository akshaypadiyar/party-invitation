package io.akshay.partyinvitation.strategy.filtering;

import io.akshay.partyinvitation.models.Customer;
import io.akshay.partyinvitation.models.Location;
import io.akshay.partyinvitation.providers.Filter;
import io.akshay.partyinvitation.services.location.EventLocationProvider;
import io.akshay.partyinvitation.services.location.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Filter based on location of the customer.
 * This implementation uses distance from a reference / event location to sort customers
 */
@Slf4j
@Service
public class LocationFiltering implements Filter<Customer> {

    private final LocationService locationService;
    private final EventLocationProvider eventLocationProvider;
    private final long range;

    public LocationFiltering(final LocationService locationService,
                             final EventLocationProvider eventLocationProvider,
                             @Value("${customers.filtering.location.range:100}") final long range) {
        this.locationService = locationService;
        this.eventLocationProvider = eventLocationProvider;
        this.range = Math.max(0, range);
    }

    @Override
    public boolean test(Customer customer) {
        Location refLocation = eventLocationProvider.get();
        double distance = locationService
                .distanceBetween(refLocation, customer.getLocation());
        return distance <= range;
    }
}

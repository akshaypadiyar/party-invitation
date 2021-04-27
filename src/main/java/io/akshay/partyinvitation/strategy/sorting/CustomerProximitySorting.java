package io.akshay.partyinvitation.strategy.sorting;

import io.akshay.partyinvitation.models.Customer;
import io.akshay.partyinvitation.models.Location;
import io.akshay.partyinvitation.services.location.EventLocationProvider;
import io.akshay.partyinvitation.services.location.LocationService;

import java.util.Comparator;

/**
 * Sorting strategy based on proximity of the customer from the event location
 */
public class CustomerProximitySorting implements Comparator<Customer> {

    private EventLocationProvider eventLocationProvider;
    private LocationService locationService;

    public CustomerProximitySorting(
            EventLocationProvider eventLocationProvider,
            LocationService locationService) {
        this.eventLocationProvider = eventLocationProvider;
        this.locationService = locationService;
    }

    /**
     * Compares customer based on their location
     *
     * @param first  {@link Customer} First customer to be compared
     * @param second {@link Customer} Second customer to be compared
     * @return Returns
     * 1 : Denotes that second customer is closer to the event location
     * 0 : Denotes that both first and second customers are equidistant
     * -1: Denotes that first customer is close to the event location
     */
    @Override
    public int compare(Customer first, Customer second) {
        return getComparator().compare(first, second);
    }

    /**
     * Calculates distance between given location & event location
     *
     * @param location {@link Location} Coordinates of the location to calculate distance
     * @return Distance in Kms from the reference location
     */
    private double calculateDistance(Location location) {
        return locationService.distanceBetween(location, eventLocationProvider.get());
    }

    /**
     * Compares based on distance of customer from event location
     *
     * @return {@link Comparator}
     */
    private Comparator<Customer> getComparator() {
        return Comparator.comparing(customer -> calculateDistance(customer.getLocation()));
    }
}

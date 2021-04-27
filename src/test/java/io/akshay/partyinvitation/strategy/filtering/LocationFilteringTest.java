package io.akshay.partyinvitation.strategy.filtering;

import io.akshay.partyinvitation.TestUtils;
import io.akshay.partyinvitation.models.Customer;
import io.akshay.partyinvitation.models.Location;
import io.akshay.partyinvitation.services.location.DistanceConverters;
import io.akshay.partyinvitation.services.location.EventLocationProvider;
import io.akshay.partyinvitation.services.location.LocationService;
import io.akshay.partyinvitation.services.location.LocationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LocationFilteringTest {

    private LocationFiltering filtering;

    private LocationService locationService;
    private EventLocationProvider eventLocationProvider;

    @BeforeEach
    void setup() {
        locationService = spy(new LocationServiceImpl(DistanceConverters.nop()));
        eventLocationProvider = spy(new EventLocationProvider(53.339428, -6.257664));

        filtering = new LocationFiltering(locationService, eventLocationProvider, 100);
    }

    @Test
    void testPositiveFiltering() {
        Customer customer = TestUtils.createCustomer(1, "Test", 53.33, -6.25);

        assertAll(
                () -> assertTrue(filtering.test(customer)),
                () -> verify(locationService, times(1)).distanceBetween(any(Location.class), any(Location.class)),
                () -> verify(eventLocationProvider, times(1)).get());

    }

    @Test
    void testFilteredOutCustomer() {
        Customer customer = TestUtils.createCustomer(1, "Test", 152.3, -206);

        assertAll(
                () -> assertFalse(filtering.test(customer)),
                () -> verify(locationService, times(1)).distanceBetween(any(Location.class), any(Location.class)),
                () -> verify(eventLocationProvider, times(1)).get());

    }


}
package io.akshay.partyinvitation.strategy.sorting;

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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CustomerProximitySortingTest {

    private CustomerProximitySorting proximitySorting;

    private LocationService locationService;
    private EventLocationProvider eventLocationProvider;

    @BeforeEach
    void setUp() {

        locationService = spy(new LocationServiceImpl(DistanceConverters.nop()));
        eventLocationProvider = spy(new EventLocationProvider(53.339428, -6.257664));

        //when(eventLocationProvider.get()).thenCallRealMethod();
        //when(locationService.distanceBetween(any(Location.class), any(Location.class))).thenCallRealMethod();

        proximitySorting = new CustomerProximitySorting(eventLocationProvider, locationService);
    }

    @Test
    void testLocationLookupCounts() {
        Customer one = TestUtils.createCustomer(10, "One", -10.34235, 103.1221);
        Customer two = TestUtils.createCustomer(2, "Two", 60.34, 19.9724);

        int distance = proximitySorting.compare(one, two);

        verify(locationService, times(2)).distanceBetween(any(Location.class), any(Location.class));
        verify(eventLocationProvider, times(2)).get();

        assertNotEquals(0, distance);
    }

    @Test
    void testLocationSorting() {
        Customer one = TestUtils.createCustomer(10, "One", -10.34235, 103.1221);
        Customer two = TestUtils.createCustomer(2, "Two", 60.34, 19.9724);

        proximitySorting = new CustomerProximitySorting(eventLocationProvider, new LocationServiceImpl(DistanceConverters.nop()));

        assertAll(
                () -> assertEquals(1, proximitySorting.compare(one, two)),
                () -> assertNotEquals(1, proximitySorting.compare(two, one)),
                () -> assertEquals(0, proximitySorting.compare(one, one)));
    }
}
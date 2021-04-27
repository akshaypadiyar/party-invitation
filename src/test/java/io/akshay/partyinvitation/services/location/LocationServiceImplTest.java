package io.akshay.partyinvitation.services.location;

import io.akshay.partyinvitation.models.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LocationServiceImplTest {

    private LocationService locationService;

    @BeforeEach
    void setup() {
        locationService = new LocationServiceImpl(DistanceConverters.nop());
    }

    @Test
    void testDistanceBetweenSameLocationShouldBeZero() {
        var location = new Location(52.2559432, -7.1048927);
        assertEquals(0, locationService.distanceBetween(location, location));
    }

    @Test
    void testDistanceBetweenTwoLocations() {
        var intercomDub = new Location(53.339428, -6.257664);
        var intercomHQ = new Location(37.78881, -122.4003);

        assertTrue(locationService.distanceBetween(intercomDub, intercomHQ) > 8000);
    }
}
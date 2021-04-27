package io.akshay.partyinvitation.services.location;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventLocationProviderTest {

    private EventLocationProvider provider;

    @Test
    void getEventLocation() {
        final double latitude = -10.1234;
        final double longitude = 20.7123;
        provider = new EventLocationProvider(latitude, longitude);

        assertAll(() -> assertEquals(latitude, provider.get().getLatitude()),
                () -> assertEquals(longitude, provider.get().getLongitude()));


    }


}
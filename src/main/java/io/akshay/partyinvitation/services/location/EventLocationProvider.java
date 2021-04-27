package io.akshay.partyinvitation.services.location;

import io.akshay.partyinvitation.models.Location;
import io.akshay.partyinvitation.providers.Provider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Get location of the event
 */
@Service
public class EventLocationProvider implements Provider<Location> {

    private double latitude;
    private double longitude;

    public EventLocationProvider(@Value("${event.location.latitude}") double latitude,
                                 @Value("${event.location.longitude}") double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public Location get() {
        return new Location(latitude, longitude);
    }
}

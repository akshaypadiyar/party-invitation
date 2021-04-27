package io.akshay.partyinvitation.services.location;

import io.akshay.partyinvitation.models.Location;

/**
 * Location Service handles operations on {@link Location}(s)
 */
public interface LocationService {

    /**
     * Calculates distance between two locations in kilometers.
     *
     * @param one {@link Location} First location
     * @param two {@link Location} Second location
     * @return Distance in kms
     */
    double distanceBetween(Location one, Location two);
}

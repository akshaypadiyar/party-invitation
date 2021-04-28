package io.akshay.partyinvitation.services.location;

import io.akshay.partyinvitation.models.Location;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static io.akshay.partyinvitation.services.location.DistanceConverters.nop;

/**
 * Location Service handles operations on {@link Location}(s)
 */
@Slf4j
@Service
public class LocationServiceImpl implements LocationService {

    private final DistanceConverter distanceConverter;

    public LocationServiceImpl(DistanceConverter distanceConverter) {
        this.distanceConverter = distanceConverter != null ? distanceConverter : DistanceConverters.nop();
    }

    /**
     * Radius of earth in kms
     */
    private final Double RADIUS_OF_EARTH = 6371.0D;

    @Override
    public double distanceBetween(Location one, Location two) {
        double deltaLatitude = Math.toRadians(two.getLatitude() - one.getLatitude());
        double deltaLongitude = Math.toRadians(two.getLongitude() - one.getLongitude());

        double centralAngle = Math.pow(Math.sin(deltaLatitude / 2), 2) + Math.pow(Math.sin(deltaLongitude / 2), 2)
                * Math.cos(Math.toRadians(one.getLatitude())) * Math.cos(Math.toRadians(two.getLatitude()));

        double chord = 2 * Math.atan2(Math.sqrt(centralAngle), Math.sqrt(1 - centralAngle));

        return distanceConverter.convert(RADIUS_OF_EARTH * chord);
    }
}


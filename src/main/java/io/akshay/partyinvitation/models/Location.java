package io.akshay.partyinvitation.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import java.io.Serial;
import java.io.Serializable;

/**
 * Represents geographical location of an entity
 */
@Validated
public class Location implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Latitude of location
     */
    private double latitude;

    /**
     * Longitude of location
     */
    private double longitude;

    public Location() {
    }

    @JsonCreator
    public Location(@NonNull @Min(0) double latitude,
                    @NonNull @Min(0) double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Get latitude of the location
     *
     * @return Latitude in degrees
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Updates latitude of the location
     *
     * @param latitude New latitude in degrees
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets longitude of the location
     *
     * @return Longitude in degrees
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Updates longitude of the location
     *
     * @param longitude new longitude in degrees
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}

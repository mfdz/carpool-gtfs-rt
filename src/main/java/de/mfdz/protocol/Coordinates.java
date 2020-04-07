package de.mfdz.protocol;

import static com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Coordinates {
    @JsonSerialize final double lat;
    @JsonSerialize final double lng;

    @JsonCreator(mode = PROPERTIES)
    public Coordinates(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }
}

package de.mfdz.protocol;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class CarpoolTrip {
    @JsonSerialize final String id;
    @JsonSerialize final Coordinates origin;
    @JsonSerialize final Coordinates destination;

    public CarpoolTrip(String id, Coordinates origin, Coordinates destination) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
    }
}

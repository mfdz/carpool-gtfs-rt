package de.mfdz.protocol;

public class CarpoolTrip {
    final String id;
    final Coordinates origin;
    final Coordinates destination;

    public CarpoolTrip(String id, Coordinates origin, Coordinates destination) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
    }
}

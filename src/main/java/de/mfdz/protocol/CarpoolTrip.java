package de.mfdz.protocol;

public class CarpoolTrip {
    public final String id;
    public final Coordinates origin;
    public final Coordinates destination;
    public final TripTime time;

    public CarpoolTrip(String id, Coordinates origin, Coordinates destination, TripTime time) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.time = time;
    }
}

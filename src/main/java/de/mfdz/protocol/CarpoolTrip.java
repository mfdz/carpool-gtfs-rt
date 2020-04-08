package de.mfdz.protocol;

import java.util.List;

public class CarpoolTrip {
    public final String id;
    public final List<Location> locations;
    public final TripTime time;

    public CarpoolTrip(String id, List<Location> locations, TripTime time) {
        this.id = id;
        this.locations = locations;
        this.time = time;
    }
}

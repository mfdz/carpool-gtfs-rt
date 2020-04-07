package de.mfdz.protocol;

import java.util.Objects;

public class Coordinates {
    final double lat;
    final double lng;

    public Coordinates(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    public boolean equals(Object obj) {
        if (getClass().equals(obj.getClass())) {
            var other = (Coordinates) obj;
            return Objects.equals(lat, other.lat) && Objects.equals(lng, other.lng);
        } else {
            return false;
        }
    }
}

package de.mfdz.protocol;

import java.util.Objects;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Location {
    final String name;
    final double lat;
    final double lng;

    public Location(String name, double lat, double lng) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    public boolean equals(Object obj) {
        if (getClass().equals(obj.getClass())) {
            var other = (Location) obj;
            return Objects.equals(name, other.name)
                    && Objects.equals(lat, other.lat)
                    && Objects.equals(lng, other.lng);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

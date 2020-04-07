package de.mfdz.protocol;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = TripTime.OneOff.class, name = "one-off"),
    @JsonSubTypes.Type(value = TripTime.Recurring.class, name = "recurring")
})
public interface TripTime {
    class OneOff implements TripTime {
        public final OffsetDateTime departureTime;

        // sadly this annotation is necessary:
        // https://github.com/FasterXML/jackson-databind/issues/1498
        public OneOff(@JsonProperty("departureTime") OffsetDateTime departureTime) {
            this.departureTime = departureTime;
        }
    }

    class Recurring implements TripTime {
        public final LocalDate startDate;
        public final LocalTime departureTime;
        public final List<DayOfWeek> weekdays;

        public Recurring(LocalDate startDate, LocalTime departureTime, List<DayOfWeek> weekdays) {
            this.startDate = startDate;
            this.departureTime = departureTime;
            this.weekdays = weekdays;
        }
    }
}

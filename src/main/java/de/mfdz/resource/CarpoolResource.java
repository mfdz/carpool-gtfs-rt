package de.mfdz.resource;

import com.codahale.metrics.annotation.Timed;
import com.google.transit.realtime.GtfsRealtime;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("carpool")
@Produces(MediaType.APPLICATION_OCTET_STREAM)
public class CarpoolResource {

    @GET
    @Timed
    public byte[] sayHello(@QueryParam("name") Optional<String> name) {

        String today = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);

        GtfsRealtime.TripDescriptor tripDescriptor =
                GtfsRealtime.TripDescriptor.newBuilder()
                        .setTripId("carpool-update-123")
                        .setStartDate(today)
                        .setScheduleRelationship(
                                GtfsRealtime.TripDescriptor.ScheduleRelationship.SCHEDULED)
                        .build();

        String herrenbergId = "1:de:08115:4512:1:2";
        String ehningenId = "1:de:08115:5773:1:1";

        ZonedDateTime threeOclock =
                LocalDate.now().atStartOfDay().plusHours(15).atZone(ZoneId.of("Europe/Berlin"));

        List<GtfsRealtime.TripUpdate.StopTimeUpdate> stopTimeUpdates =
                Arrays.asList(
                        buildStopTimeUpdate(herrenbergId, threeOclock),
                        buildStopTimeUpdate(ehningenId, threeOclock.plusMinutes(30)));

        GtfsRealtime.TripUpdate update =
                GtfsRealtime.TripUpdate.newBuilder()
                        .setTrip(tripDescriptor)
                        .addAllStopTimeUpdate(stopTimeUpdates)
                        .build();

        return update.toByteArray();
    }

    private GtfsRealtime.TripUpdate.StopTimeUpdate buildStopTimeUpdate(
            String stopId, ZonedDateTime time) {
        GtfsRealtime.TripUpdate.StopTimeEvent timeEvent =
                GtfsRealtime.TripUpdate.StopTimeEvent.newBuilder()
                        .setTime(time.toEpochSecond())
                        .build();
        return GtfsRealtime.TripUpdate.StopTimeUpdate.newBuilder()
                .setDeparture(timeEvent)
                .setArrival(timeEvent)
                .setStopId(stopId)
                .build();
    }
}

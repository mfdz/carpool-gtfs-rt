package de.mfdz.resource;

import static com.google.transit.realtime.GtfsRealtime.*;

import com.codahale.metrics.annotation.Timed;
import de.mfdz.RealtimeExtension;
import de.mfdz.RealtimeExtension.MfdzTripDescriptorExtension;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("carpool")
@Produces(MediaType.APPLICATION_OCTET_STREAM)
public class CarpoolResource {

    @GET
    @Timed
    public byte[] carpoolFeed() {

        var today = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);

        var withTripUrl =
                MfdzTripDescriptorExtension.newBuilder()
                        .setTripUrl("https://de.wikipedia.org/wiki/Herrenberg")
                        .build();

        var tripDescriptor =
                TripDescriptor.newBuilder()
                        .setTripId("carpool-update-123")
                        .setStartDate(today)
                        .setScheduleRelationship(TripDescriptor.ScheduleRelationship.ADDED)
                        .setExtension(RealtimeExtension.tripDescriptor, withTripUrl)
                        .build();

        var herrenbergId = "de:08115:4512:1:2";
        var ehningenId = "de:08115:5773:1:1";

        var threeOclock =
                LocalDate.now().atStartOfDay().plusHours(15).atZone(ZoneId.of("Europe/Berlin"));

        var stopTimeUpdates =
                Arrays.asList(
                        buildStopTimeUpdate(herrenbergId, threeOclock),
                        buildStopTimeUpdate(ehningenId, threeOclock.plusMinutes(30)));

        var update =
                TripUpdate.newBuilder()
                        .setTrip(tripDescriptor)
                        .addAllStopTimeUpdate(stopTimeUpdates)
                        .build();

        var header =
                FeedHeader.newBuilder()
                        .setIncrementality(FeedHeader.Incrementality.FULL_DATASET)
                        .setTimestamp(Instant.now().getEpochSecond())
                        .setGtfsRealtimeVersion("1.0");

        var entity =
                FeedEntity.newBuilder().setId(update.getTrip().getTripId()).setTripUpdate(update);

        var feedMessage = FeedMessage.newBuilder().setHeader(header).addEntity(entity).build();

        return feedMessage.toByteArray();
    }

    private TripUpdate.StopTimeUpdate buildStopTimeUpdate(String stopId, ZonedDateTime time) {
        var timeEvent = TripUpdate.StopTimeEvent.newBuilder().setTime(time.toEpochSecond()).build();
        return TripUpdate.StopTimeUpdate.newBuilder()
                .setDeparture(timeEvent)
                .setArrival(timeEvent)
                .setStopId(stopId)
                .build();
    }
}

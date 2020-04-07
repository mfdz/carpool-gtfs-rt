package de.mfdz;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import de.mfdz.protocol.CarpoolTrip;
import de.mfdz.protocol.Coordinates;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.junit.jupiter.api.Test;

public class CarpoolTripJsonTest {

    InputStream getFile(String name) {
        return this.getClass().getClassLoader().getResourceAsStream(name);
    }

    @Test
    void readTrips() throws IOException {
        var herrenberg = new Coordinates(48.5997, 8.8859);

        var mapper =
                JsonMapper.builder()
                        .addModule(new ParameterNamesModule(JsonCreator.Mode.PROPERTIES))
                        .addModule(new JavaTimeModule())
                        .build();

        var reader = mapper.readerFor(new TypeReference<List<CarpoolTrip>>() {});
        List<CarpoolTrip> parsedTrips = reader.readValue(getFile("carpool-trips.json"));

        assertEquals(parsedTrips.size(), 2);
        var first = parsedTrips.get(0);
        assertEquals(first.origin, herrenberg);
        assertEquals(first.destination, herrenberg);
    }
}

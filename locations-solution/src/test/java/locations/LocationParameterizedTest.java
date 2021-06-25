package locations;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class LocationParameterizedTest {

    LocationParser locationParser = new LocationParser();

    @ParameterizedTest
    @MethodSource("createLocations")
    void testIsOnPrimeMeridian(Location location, boolean expected) {
        assertEquals(expected, locationParser.isOnPrimeMeridian(location));
    }

    static Stream<Arguments> createLocations() {
        return Stream.of(
                arguments(new Location("p1", 47.497912, 19.040235), false),
                arguments(new Location("p2", 47.497912, 0), true),
                arguments(new Location("p3", 12, 19.040235), false),
                arguments(new Location("p4", 47.497912, 0), true),
                arguments(new Location("p5", 0, 21), false),
                arguments(new Location("p6", 47.497912, 19.040235), false)
        );
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    void testDistanceFromCsvFile(String lat1, String lon1, String lat2, String lon2, double distance) {
        Location location1 = locationParser.parse("," + lat1 + "," + lon1);
        Location location2 = locationParser.parse("," + lat2 + "," + lon2);
        assertEquals(distance / 1000, location1.distanceFrom(location2), 0.1);
    }
}

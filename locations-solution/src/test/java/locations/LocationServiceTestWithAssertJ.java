package locations;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class LocationServiceTestWithAssertJ {

    LocationService locationService = new LocationService();
    List<Location> locations =
            locationService.readLocations(Path.of("src/test/java/resources/locations.csv"));

    @Test
    void readLocationsTest() {
        assertThat(locations)
                .hasSize(6)
                .extracting(Location::getName)
                .contains("Róma");

        assertThat(locations)
                .filteredOn(l -> l.getLon() < 0)
                .extracting(Location::getName, Location::getLat)
                .containsOnly(
                        tuple("Reykjavik", 64.126521),
                        tuple("Lima", -12.046374)
                );
    }
}

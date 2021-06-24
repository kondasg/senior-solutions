package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@LocationOperationsFeatureTest
class LocationOperatorsTest {

    LocationOperators locationOperators;
    List<Location> locations = new ArrayList<>();

    @BeforeEach
    void init() {
        locationOperators = new LocationOperators();

        locations.add(new Location("Budapest", 47.497912, 19.040235));
        locations.add(new Location("Reykjavik", 64.126521, -21.817439));
        locations.add(new Location("Canberra", -35.282, 149.128684));
        locations.add(new Location("Oslo", 59.913869, 10.752245));
        locations.add(new Location("Lima", -12.046374, -77.042793));
        locations.add(new Location("Róma", 41.902784, 12.496366));
    }

    @Test
    void testFilterOnNorth() {
        List<Location> result = locationOperators.filterOnNorth(locations);
        assertEquals(4, result.size());

        assertEquals(
                List.of("Budapest", "Reykjavik", "Oslo", "Róma"),
                result.stream().map(Location::getName).collect(Collectors.toList())
        );

    }
}
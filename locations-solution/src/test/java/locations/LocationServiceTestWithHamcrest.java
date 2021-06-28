package locations;

import org.junit.Test;

import java.nio.file.Path;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LocationServiceTestWithHamcrest {

    LocationService locationService = new LocationService();

    @Test
    public void readLocationsTest() {
        List<Location> locations =
                locationService.readLocations(Path.of("src/test/java/resources/locations.csv"));

        assertThat(locations, hasItem(hasProperty("name", equalTo("Budapest"))));
        assertThat(locations, hasItem(hasProperty("lat", closeTo(-35, 0.5))
        ));
    }

}

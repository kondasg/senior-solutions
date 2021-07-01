package locations;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LocationsServiceTest {

    @Test
    void testGetLocations() {
        LocationsService service = new LocationsService(new ModelMapper());
        List<LocationDto> locations = service.getLocations();

        assertThat(locations).extracting("name").contains("Point 1", "Point 2");
    }
}
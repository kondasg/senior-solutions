package locations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class LocationsIT {

    @Autowired
    LocationsController locationsController;

    @Test
    void getLocations() {
        List<LocationDto> l = locationsController.getLocations();

        assertThat(l).extracting("name").contains("Point 1");
    }
}

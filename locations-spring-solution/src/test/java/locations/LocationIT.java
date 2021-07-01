package locations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class LocationIT {

    @Autowired
    LocationsController locationsController;

    @Test
    void getLocations() {
        List<LocationDto> l = locationsController.getLocations(Optional.empty());

        assertThat(l).extracting("name").contains("Point 1");
    }
}

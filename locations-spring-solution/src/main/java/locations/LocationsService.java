package locations;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationsService {

    private List<Location> locations = new ArrayList<>(List.of(
            new Location(1, "Point 1", 48.1234, 19.1594),
            new Location(1, "Point 2", 47.9544, 18.9999)
    ));

    public List<Location> getLocations() {
        return locations;
    }

}

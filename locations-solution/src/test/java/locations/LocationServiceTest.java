package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LocationServiceTest {

    List<Location> locations = new ArrayList<>();

    @BeforeEach
    void init() {
        locations.add(new Location("Budapest", 47.497912, 19.040235));
        locations.add(new Location("Reykjavik", 64.126521, -21.817439));
        locations.add(new Location("Canberra", -35.282, 149.128684));
        locations.add(new Location("Oslo", 59.913869, 10.752245));
        locations.add(new Location("Lima", -12.046374, -77.042793));
        locations.add(new Location("Róma", 41.902784, 12.496366));
    }

    @TempDir
    Path tempDir;

    @Test
    void writeLocations() throws IOException {
        Path file = tempDir.resolve("locations.txt");
        new LocationService().writeLocations(file, locations);
        List<String> lines = Files.readAllLines(file);
        assertEquals("Reykjavik,64.126521,-21.817439", lines.get(1));
    }
}
package locations;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class LocationService {

    public void writeLocations(Path file, List<Location> locations) {
        try (BufferedWriter writer = Files.newBufferedWriter(file)) {
            writeLines(locations, writer);
        } catch (IOException ioe) {
            throw new IllegalStateException("Can't open file", ioe);
        }
    }

    private void writeLines(List<Location> locations, BufferedWriter writer) {
        try {
            for (Location location : locations) {
                writer.write(location.getName() + "," + location.getLat() + "," + location.getLon());
                writer.newLine();
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Can't write file", ioe);
        }
    }
}

package locations;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
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

    public List<Location> readLocations(Path file) {
        List<Location> result = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            readLines(result, reader);
        } catch (IOException ioe) {
            throw new IllegalStateException("Can't read file", ioe);
        }
        return result;
    }

    private void readLines(List<Location> result, BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] splittedLine = line.split(",");
            result.add(new Location(
                    splittedLine[0],
                    Double.parseDouble(splittedLine[1]),
                    Double.parseDouble(splittedLine[2]))
            );
        }
    }
}

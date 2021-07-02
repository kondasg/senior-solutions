package bikes;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BikeService {

    private final ModelMapper modelMapper;
    List<Bike> bikes = new ArrayList<>();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public BikeService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<Bike> getBikes() {
        return bikes;
    }

    public List<BikeDto> getHistory() {
        Type tagetListType = new TypeToken<List<BikeDto>>(){}.getType();
        getBikeList();
        return modelMapper.map(bikes, tagetListType);
    }

    private void getBikeList() {
        if (bikes.size() == 0) {
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(new ClassPathResource("/bikes.csv").getInputStream()))) {
                fillBikes(reader);
            } catch (IOException ioe) {
                throw new IllegalStateException("Can't read file", ioe);
            }
        }
    }

    private void fillBikes(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] splittedLine = line.split(";");
            bikes.add(new Bike(
                    splittedLine[0],
                    splittedLine[1],
                    LocalDateTime.parse(splittedLine[2], dateTimeFormatter),
                    Double.parseDouble(splittedLine[3])
            ));
        }
    }

    public List<String> getUsers() {
        if (bikes.size() == 0) {
            getBikeList();
        }
        return bikes.stream()
                .map(Bike::getUserId)
                .distinct()
                .collect(Collectors.toList());
    }
}

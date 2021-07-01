package locations;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class LocationsService {

    private ModelMapper modelMapper;
    private AtomicLong idGenerator = new AtomicLong();

    private List<Location> locations = new ArrayList<>(List.of(
            new Location(idGenerator.incrementAndGet(), "Point 1", 48.1234, 19.1594),
            new Location(idGenerator.incrementAndGet(), "Point 2", 47.9544, 18.9999)
    ));

    public LocationsService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<LocationDto> getLocations(Optional<String> name) {
        Type tagetListType = new TypeToken<List<LocationDto>>(){}.getType();
        List<Location> filtered = locations.stream()
                .filter(l -> name.isEmpty() || l.getName().equalsIgnoreCase(name.get()))
                .collect(Collectors.toList());
        return modelMapper.map(filtered, tagetListType);
    }

    public LocationDto findLocationById(long id) {
        return modelMapper.map(locations.stream()
                .filter(l -> l.getId() == id)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("not found")), LocationDto.class);
    }

    public LocationDto createLocation(CreateLocationCommand command) {
        Location location = new Location(idGenerator.incrementAndGet(), command.getName());
        locations.add(location);
        return modelMapper.map(location, LocationDto.class);
    }

    public LocationDto updateLocation(long id, UpdateLocationCommand command) {
        Location location = locations.stream()
                .filter(l -> l.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("not found"));
        location.setName(command.getName());
        location.setLat(command.getLat());
        location.setLon(command.getLon());
        return modelMapper.map(location, LocationDto.class);
    }
}

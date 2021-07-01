package locations;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.internal.bytebuddy.description.method.MethodDescription;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class LocationsService {

    private ModelMapper modelMapper;

    private List<Location> locations = new ArrayList<>(List.of(
            new Location(1, "Point 1", 48.1234, 19.1594),
            new Location(1, "Point 2", 47.9544, 18.9999)
    ));

    public LocationsService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<LocationDto> getLocations() {
        Type tagetListType = new TypeToken<List<LocationDto>>(){}.getType();
        return modelMapper.map(locations, tagetListType);
    }

}

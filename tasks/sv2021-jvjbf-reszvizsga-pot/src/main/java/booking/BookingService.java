package booking;

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
public class BookingService {

    private List<Accommodation> accommodations = new ArrayList<>();
    private ModelMapper modelMapper;
    private AtomicLong atomicLong = new AtomicLong();

    public BookingService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public void deleteAllAccommodations() {
        accommodations.clear();
        atomicLong = new AtomicLong();
    }

    public AccommodationDTO createAccommodation(CreateAccommodationCommand command) {
        Accommodation accommodation = new Accommodation(
                atomicLong.incrementAndGet(),
                command.getName(),
                command.getCity(),
                command.getMaxCapacity(),
                command.getPrice()
        );
        accommodations.add(accommodation);
        return modelMapper.map(accommodation, AccommodationDTO.class);
    }

    public List<AccommodationDTO> getAllAccommodations(Optional<String> city) {
        Type targetListType = new TypeToken<List<AccommodationDTO>>() {
        }.getType();
        List<Accommodation> filtered = accommodations.stream()
                .filter(a -> city.isEmpty() || a.getCity().equalsIgnoreCase(city.get()))
                .collect(Collectors.toList());
        return modelMapper.map(filtered, targetListType);
    }

    public AccommodationDTO getAccommodation(Long id) {
        return modelMapper.map(getAccommodationById(id), AccommodationDTO.class);
    }

    public AccommodationDTO updateAccommodationPrice(Long id, UpdatePriceCommand command) {
        Accommodation accommodation = getAccommodationById(id);
        if (accommodation.getPrice() != command.getPrice()) {
            accommodation.setPrice(command.getPrice());
        }
        return modelMapper.map(accommodation, AccommodationDTO.class);
    }

    public AccommodationDTO addReservation(Long id, CreateReservationCommand command) {
        Accommodation accommodation = getAccommodationById(id);
        accommodation.addReservation(command.getNumberOfPeople());
        return modelMapper.map(accommodation, AccommodationDTO.class);
    }

    private Accommodation getAccommodationById(Long id) {
        return accommodations.stream()
                .filter(a -> a.getId() == (id))
                .findFirst()
                .orElseThrow(() -> new NotFoundAccommodationException("Accommodation id not found: " + id));
    }
}

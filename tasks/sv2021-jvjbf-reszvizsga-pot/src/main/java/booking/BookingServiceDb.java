package booking;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookingServiceDb {

    private ModelMapper modelMapper;
    private BookingRepository bookingRepository;

    public void deleteAllAccommodations() {
        bookingRepository.deleteAll();
    }

    public AccommodationDTO createAccommodation(CreateAccommodationCommand command) {
        Accommodation accommodation = new Accommodation(
                command.getName(),
                command.getCity(),
                command.getMaxCapacity(),
                command.getPrice()
        );
        bookingRepository.save(accommodation);
        return modelMapper.map(accommodation, AccommodationDTO.class);
    }

    public List<AccommodationDTO> getAllAccommodations(Optional<String> city) {
        Type targetListType = new TypeToken<List<AccommodationDTO>>() {}.getType();
        List<Accommodation> filtered = city.isEmpty()
                ? bookingRepository.findAll()
                : bookingRepository.findByCityEquals(city.get());
        return modelMapper.map(filtered, targetListType);
    }

    public AccommodationDTO getAccommodation(Long id) {
        return modelMapper.map(getAccommodationById(id), AccommodationDTO.class);
    }

    @Transactional
    public AccommodationDTO updateAccommodationPrice(Long id, UpdatePriceCommand command) {
        Accommodation accommodation = getAccommodationById(id);
        if (accommodation.getPrice() != command.getPrice()) {
            accommodation.setPrice(command.getPrice());
        }
        return modelMapper.map(accommodation, AccommodationDTO.class);
    }

    @Transactional
    public AccommodationDTO addReservation(Long id, CreateReservationCommand command) {
        Accommodation accommodation = getAccommodationById(id);
        accommodation.addReservation(command.getNumberOfPeople());
        return modelMapper.map(accommodation, AccommodationDTO.class);
    }

    private Accommodation getAccommodationById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new NotFoundAccommodationException("Accommodation id not found: " + id));
    }
}

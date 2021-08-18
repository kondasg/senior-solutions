package booking;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/accommodations")
public class BookingController {

    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @DeleteMapping
    public void deleteAllAccommodations() {
        bookingService.deleteAllAccommodations();
    }

    @PostMapping
    public AccommodationDTO createAccommodation(@Valid @RequestBody CreateAccommodationCommand command) {
        return bookingService.createAccommodation(command);
    }

    @GetMapping
    public List<AccommodationDTO> getAllAccommodations(@RequestParam(name = "city") Optional<String> city) {
        return bookingService.getAllAccommodations(city);
    }

    @GetMapping("/{id}")
    public AccommodationDTO getAccommodation(@PathVariable(name = "id") Long id) {
        return bookingService.getAccommodation(id);
    }

    @PutMapping("/{id}")
    public AccommodationDTO updateAccommodationPrice(@PathVariable(name = "id") Long id, @RequestBody UpdatePriceCommand command) {
        return bookingService.updateAccommodationPrice(id, command);
    }

    @PostMapping("/{id}/book")
    public AccommodationDTO addReservation(@PathVariable(name = "id") Long id, @RequestBody CreateReservationCommand command) {
        return bookingService.addReservation(id, command);
    }
}

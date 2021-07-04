package fleamarket;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/fleamarket/advertisement")
public class FleamarketController {

    private FleamarketService fleamarketService;

    public FleamarketController(FleamarketService fleamarketService) {
        this.fleamarketService = fleamarketService;
    }

    @DeleteMapping("/all")
    public void deleteAllAdvertisements() {
        fleamarketService.deleteAllAdvertisements();
    }

    @PostMapping
    public AdvertisementDTO addAdvertisement(@Valid @RequestBody CreateAdvertisementCommand command) {
        return fleamarketService.addAdvertisement(command);
    }

    @GetMapping
    public List<AdvertisementDTO> listAdvertisements(
            @RequestParam(name = "category") Optional<String> category,
            @RequestParam(name = "word") Optional<String> word) {
        return fleamarketService.listAdvertisements(category, word);
    }

    @PutMapping("/{id}")
    public AdvertisementDTO modifyAdvertisementTextById(@PathVariable("id") long id, @RequestBody UpdateAdvertisementCommand command) {
        return fleamarketService.modifyAdvertisementTextById(id, command);
    }

    @GetMapping("/{id}")
    public AdvertisementDTO getAdvertisementById(@PathVariable("id") long id) {
        return fleamarketService.getAdvertisementById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAdvertisementById(@PathVariable("id") long id) {
        fleamarketService.deleteAdvertisementById(id);
    }

    @DeleteMapping
    public void deleteOldAdvertisementAllCategory(@RequestParam(name = "category") Optional<String> category) {
        fleamarketService.deleteOldAdvertisementAllCategory(category);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Problem> handleNotFound(IllegalArgumentException le) {
        Problem problem = Problem.builder()
                .withType(URI.create("fleamarket/not-found"))
                .withTitle("Not found")
                .withStatus(Status.NOT_FOUND)
                .withDetail(le.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Problem> handleValidExpection(MethodArgumentNotValidException ex) {
        List<Violation> violations = ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> new Violation(fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList());
        Problem problem = Problem.builder()
                .withType(URI.create("fleamarket/not-valid"))
                .withTitle("Validation error")
                .withStatus(Status.BAD_REQUEST)
                .withDetail(ex.getMessage())
                .with("violations", violations)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

}

package movie;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/movies")
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieDto> getFilms(@RequestParam Optional<String> title) {
        return movieService.getFilms(title);
    }

    @GetMapping("/{id}")
    public MovieDto getFilmById(@PathVariable("id") int id) {
        return movieService.getFilmById(id);
    }

    @PostMapping
    public MovieDto createFilm(@RequestBody CreateFilmCommand command) {
        return movieService.createFilm(command);
    }

    @PostMapping("/{id}/rating")
    public double addRating(@PathVariable("id") int id, @RequestBody AddRatingCommand command) {
        return movieService.addRating(id, command);
    }

    @DeleteMapping
    public void deleteFilm(@RequestParam String title) {
        movieService.deleteFilm(title);
    }

}

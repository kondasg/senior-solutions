package movie;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private ModelMapper modelMapper;
    private AtomicInteger atomicInteger = new AtomicInteger();
    private List<Movie> movies = new ArrayList<>(List.of(
            new Movie(atomicInteger.incrementAndGet(), "A", 90),
            new Movie(atomicInteger.incrementAndGet(), "B", 92),
            new Movie(atomicInteger.incrementAndGet(), "C", 87),
            new Movie(atomicInteger.incrementAndGet(), "D", 120)
    ));

    public MovieService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<MovieDto> getFilms(Optional<String> title) {
        Type tagetListType = new TypeToken<List<MovieDto>>() {
        }.getType();
        if (title.isPresent()) {
            return modelMapper.map(
                    movies.stream()
                            .filter(m -> m.getTitle().toLowerCase().contains(title.get().toLowerCase()))
                            .collect(Collectors.toList()),
                    tagetListType);
        }
        return modelMapper.map(movies, tagetListType);
    }

    public MovieDto getFilmById(int id) {
        return modelMapper.map(movies.stream()
                        .filter(m -> m.getId() == id)
                        .findFirst()
                        .orElseThrow(() -> new IllegalStateException("Not found")),
                MovieDto.class);
    }

    public MovieDto createFilm(CreateFilmCommand command) {
        Movie movie = new Movie(atomicInteger.incrementAndGet(), command.getTitle(), command.getLength());
        movies.add(movie);
        return modelMapper.map(movie, MovieDto.class);
    }

    public void deleteFilm(String title) {
        movies.remove(
                movies.stream()
                .filter(m -> m.getTitle().equals(title))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Not found"))
        );
    }

    public double addRating(int id, AddRatingCommand command) {
        Movie movie = movies.stream()
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Not found"));
        movie.addRating(command.getRating());
        return movie.getAverageRating();
    }
}

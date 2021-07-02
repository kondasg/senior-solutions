package movie;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    private Movie movie = new Movie(1, "AAA", 90 );

    @Test
    void addRating() {
        assertThat(movie.getRatings()).hasSize(0);
        assertEquals(0, movie.getAverageRating());

        movie.addRating(5.2);
        movie.addRating(6.8);
        movie.addRating(3.1);
        movie.addRating(9.9);

        assertThat(movie.getRatings()).hasSize(4);
        assertEquals(6.25, movie.getAverageRating(), 0.1);
    }
}
package movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    private int id;
    private String title;
    private int length;
    private List<Double> ratings = new ArrayList<>();
    private double averageRating;

    public Movie(int id, String title, int length) {
        this.id = id;
        this.title = title;
        this.length = length;
    }

    public void addRating(double rating) {
        ratings.add(rating);
        averageRating = ratings.stream()
                .collect(Collectors.averagingDouble(Double::doubleValue));
    }
}

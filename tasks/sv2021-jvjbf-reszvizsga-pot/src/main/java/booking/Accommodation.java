package booking;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Accommodation {

    private Long id;
    private String name;
    private String city;
    private List<Integer> reservations;
    private int maxCapacity;
    private int availableCapacity;
    private int price;

    public Accommodation(Long id, String name, String city, int maxCapacity, int price) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.reservations = new ArrayList<>();
        this.maxCapacity = maxCapacity;
        this.availableCapacity = maxCapacity;
        this.price = price;
    }

    public void addReservation(int numberOfPeople) {
        if (numberOfPeople > availableCapacity) {
            throw new NotEnoughCapacityException("Not enough capacity");
        }
        availableCapacity -= numberOfPeople;
        reservations.add(numberOfPeople);
    }
}

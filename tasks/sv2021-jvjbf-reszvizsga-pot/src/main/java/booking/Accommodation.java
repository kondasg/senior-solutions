package booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Accommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String city;
    @ElementCollection
    private List<Integer> reservations;
    @Column(name = "max_capacity")
    private int maxCapacity;
    @Column(name = "available_capacity")
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

    public Accommodation(String name, String city, int maxCapacity, int price) {
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

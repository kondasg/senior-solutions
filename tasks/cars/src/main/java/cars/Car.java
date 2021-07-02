package cars;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    private String brand;
    private String type;
    private int year;
    private Condition condition;
    private List<KmState> kms;

    public Car(String brand, String type, int year, Condition condition) {
        this.brand = brand;
        this.type = type;
        this.year = year;
        this.condition = condition;
    }
}

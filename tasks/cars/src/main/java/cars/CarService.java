package cars;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private ModelMapper modelMapper;

    private List<Car> cars = new ArrayList<>(List.of(
            new Car("Opel", "Kadett", 2000, Condition.NORMAL,
                    List.of(new KmState(LocalDate.of(2000, 1, 1), 50000), new KmState(LocalDate.of(2020, 1, 1), 100000))),
            new Car("Renault", "Megane", 2010, Condition.EXCELLENT,
                    List.of(new KmState(LocalDate.of(2012, 1, 1), 1214), new KmState(LocalDate.of(20020, 1, 1), 190000))),
            new Car("Ford", "Escort", 2015, Condition.EXCELLENT,
                    List.of(new KmState(LocalDate.of(2000, 1, 1), 50000), new KmState(LocalDate.of(2020, 1, 1), 100000))),
            new Car("Ford", "Kia", 2001, Condition.NORMAL,
                    List.of(new KmState(LocalDate.of(2012, 1, 1), 1214), new KmState(LocalDate.of(20020, 1, 1), 190000)))
    ));

    public CarService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<CarDto> list() {
        Type tagetListType = new TypeToken<List<CarDto>>(){}.getType();
        return modelMapper.map(cars, tagetListType);
    }

    public List<String> listType() {
        return cars.stream()
                .map(Car::getBrand)
                .distinct()
                .collect(Collectors.toList());
    }
}

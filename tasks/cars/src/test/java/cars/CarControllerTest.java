package cars;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarControllerTest {

    @Mock
    CarService carService;

    @InjectMocks
    CarController carController;

    @Test
    void list() {

        List<Car> cars = new ArrayList<>(List.of(
                new Car("Opel", "Kadett", 2000, Condition.NORMAL,
                        List.of(new KmState(LocalDate.of(2000, 1, 1), 50000), new KmState(LocalDate.of(2020, 1, 1), 100000))),
                new Car("Renault", "Megane", 2010, Condition.EXCELLENT,
                        List.of(new KmState(LocalDate.of(2012, 1, 1), 1214), new KmState(LocalDate.of(20020, 1, 1), 190000))),
                new Car("Ford", "Escort", 2015, Condition.EXCELLENT,
                        List.of(new KmState(LocalDate.of(2000, 1, 1), 50000), new KmState(LocalDate.of(2020, 1, 1), 100000))),
                new Car("Ford", "Kia", 2001, Condition.NORMAL,
                        List.of(new KmState(LocalDate.of(2012, 1, 1), 1214), new KmState(LocalDate.of(20020, 1, 1), 190000)))
        ));

        Type tagetListType = new TypeToken<List<CarDto>>(){}.getType();

        when(carService.list()).thenReturn(new ModelMapper().map(cars, tagetListType));

        assertThat(carController.list())
                .hasSize(4)
                .extracting(CarDto::getBrand)
                .contains("Ford", "Opel");

        verify(carService, times(1)).list();
    }

    @Test
    void listType() {
        when(carService.listType()).thenReturn(List.of("Ford", "Opel"));

        assertThat(carController.listType())
                .hasSize(2)
                .contains("Ford", "Opel");

        verify(carService).listType();
    }
}
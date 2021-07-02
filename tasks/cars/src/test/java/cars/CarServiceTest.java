package cars;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CarServiceTest {

    CarService carService = new CarService(new ModelMapper());

    @Test
    void list() {
        assertThat(carService.list())
                .hasSize(4)
                .extracting(CarDto::getBrand)
                .contains("Ford", "Opel");
    }

    @Test
    void listType() {
        assertThat(carService.listType())
                .hasSize(3)
                .contains("Ford", "Opel");
    }
}
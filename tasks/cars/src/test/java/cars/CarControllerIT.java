package cars;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CarControllerIT {

    @Autowired
    CarController carController;

    @Test
    void list() {
        assertThat(carController.list())
                .hasSize(4)
                .extracting(CarDto::getBrand)
                .contains("Ford", "Opel");
    }

    @Test
    void listType() {
        assertThat(carController.listType())
                .hasSize(3)
                .contains("Ford", "Opel");
    }
}

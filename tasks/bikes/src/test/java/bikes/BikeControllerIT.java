package bikes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BikeControllerIT {

    @Autowired
    BikeController bikeController;

    @Test
    void getHistory() {
        List<BikeDto> bikes = bikeController.getHistory();

        assertThat(bikes).extracting(BikeDto::getUserId).contains("US3434", "US346");
    }
    }

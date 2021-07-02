package bikes;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BikeServiceTest {

    BikeService bikeService = new BikeService(new ModelMapper());

    @Test
    void getHistory() {
        assertThat(bikeService.getBikes()).hasSize(0);
        List<BikeDto> bikes = bikeService.getHistory();
        assertThat(bikes).hasSize(5).extracting("userId").contains("US3334", "US336");
    }

    @Test
    void getUsers() {
        assertThat(bikeService.getUsers()).contains("US3334", "US336");
    }
}
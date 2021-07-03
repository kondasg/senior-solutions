package locations;

import org.assertj.core.util.VisibleForTesting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = LocationsController.class)
public class LocationsControllerWebMvcIT {

    @MockBean
    LocationsService locationsService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void getLocationsTest() throws Exception {
        when(locationsService.getLocations(any())).thenReturn(
                List.of(new LocationDto(1, "a", 1, 1), new LocationDto(2, "b", 2, 2))
        );
        mockMvc.perform(get("/locations"))
                //.andDo(print());
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", equalTo("a")));
    }

    @Test
    void findLocationByIdTest() throws Exception {
        when(locationsService.findLocationById(2)).thenReturn(new LocationDto(2, "b", 2, 2));
        mockMvc.perform(get("/locations/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("b")));
    }

    @Test
    void createLocationdTest() throws Exception {
        when(locationsService.createLocation(any())).thenReturn(new LocationDto(1, "b", 0, 0));
        mockMvc.perform(post("/locations")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\" : \"b\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo("b")));
    }

}

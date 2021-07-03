package locations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LocationsControllerRestTemplateIT {

    @Autowired
    TestRestTemplate template;

    @Autowired
    LocationsService locationsService;

    @Test
    void getLocationsTest() {
        locationsService.deleteAllLocations();

        LocationDto locationDto =
                template.postForObject("/locations", new CreateLocationCommand("a"), LocationDto.class);
        assertEquals("a", locationDto.getName());

        template.postForObject("/locations", new CreateLocationCommand("b"), LocationDto.class);

        List<LocationDto> locationDtos = template.exchange("/locations",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<LocationDto>>() {})
                .getBody();
        assertThat(locationDtos)
                .extracting(LocationDto::getName)
                .containsExactly("a", "b");
    }
}

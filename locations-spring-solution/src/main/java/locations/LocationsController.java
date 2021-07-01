package locations;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/locations")
public class LocationsController {

    @GetMapping
    public String getLocations() {
        return "Kedvenc helyek listája";
    }
}

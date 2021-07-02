package bikes;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BikeController {

    private final BikeService bikeService;

    public BikeController(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    @GetMapping("/histrory")
    public List<BikeDto> getHistory() {
        return bikeService.getHistory();
    }

    @GetMapping("/users")
    public List<String> getUsers() {
        return bikeService.getUsers();
    }
}

package appointment;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class NavController {

    private NavService navService;

    public NavController(NavService navService) {
        this.navService = navService;
    }

    @GetMapping("/api/types")
    public List<CaseTypeDTO> getTypes() {
        return navService.getTypes();
    }

    @PostMapping("/api/appointments")
    public AppointmentDTO addAppointment(@Valid @RequestBody CreateaddAppointment command) {
        return navService.addAppointment(command);
    }
}

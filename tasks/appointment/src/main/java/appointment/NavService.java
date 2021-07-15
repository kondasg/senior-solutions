package appointment;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class NavService {

    //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private List<CaseType> caseTypes = new ArrayList<>(List.of(
            new CaseType("001", "Adóbevallás"),
            new CaseType("002", "Befizetés"),
            new CaseType("003", "Folyószámla-egyeztetés"),
            new CaseType("004", "Adóhatósíági igazolás ügyintézése")
    ));

    private ModelMapper modelMapper;

    public NavService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<CaseType> getCaseTypes() {
        return caseTypes;
    }

    public List<CaseTypeDTO> getTypes() {
        Type targetListType = new TypeToken<List<CaseTypeDTO>>() {}.getType();
        return modelMapper.map(caseTypes, targetListType);
    }

    public AppointmentDTO addAppointment(CreateaddAppointment command) {
        return new AppointmentDTO(
                command.getTaxNumber(),
                command.getCaseType(),
                command.getInterval());
    }
}

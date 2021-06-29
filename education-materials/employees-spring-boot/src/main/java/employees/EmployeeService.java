package employees;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.internal.bytebuddy.description.method.MethodDescription;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private ModelMapper modelMapper;

    private List<Employee> employees = Collections.synchronizedList(new ArrayList<>(List.of(
            new Employee(1, "John"),
            new Employee(2, "Jack")
    )));

    public EmployeeService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<EmployeeDto> listEmployees(Optional<String> prefix) {
        Type targetListType = new TypeToken<List<EmployeeDto>>(){}.getType();
        List<Employee> filtered = employees.stream()
                .filter(e -> prefix.isEmpty() || e.getName().toLowerCase().startsWith(prefix.get().toLowerCase()))
                .collect(Collectors.toList());
        return modelMapper.map(filtered, targetListType);
    }

    public EmployeeDto findEmployeeById(long id) {
        return  modelMapper.map(employees.stream()
                .filter(e -> e.getId() == id)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Not found: " + id)),
                EmployeeDto.class);
    }
}

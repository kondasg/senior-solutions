package cars;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class KmState {

    private LocalDate date;
    private int km;
}

package appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateaddAppointment {

    @Cdv
    private String taxNumber;
    @IsCaseType
    private String caseType;
    @Date
    private Interval interval;
}

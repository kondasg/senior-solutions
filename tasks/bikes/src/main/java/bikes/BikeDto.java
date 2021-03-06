package bikes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BikeDto {

    private String bikeId;
    private String userId;
    private LocalDateTime date;
    private double distace;
}

package booking;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class CreateAccommodationCommand {

    @NotBlank
    private String name;
    @NotBlank
    private String city;
    @Min(value = 10)
    private int maxCapacity;
    private int price;
}

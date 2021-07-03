package locations;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UpdateLocationCommand {

    @NotBlank(message = "Name can't be blank")
    private String name;
    @Min(value = -90, message = "The lat should be between -90 and 90")
    @Max(value = 90, message = "The lat should be between -90 and 90")
    private double lat;
    @Min(value = -180, message = "The lat should be between -180 and 180")
    @Max(value = 180, message = "The lat should be between -180 and 180")
    private double lon;
}

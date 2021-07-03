package locations;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateLocationCommand {

    @NotBlank(message = "Name can't be blank")
    private String name;
    @Coordinate(min = -90, max = 90, message = "The lat should be between -90 and 90")
    private double lat;
    @Coordinate(min = -180, max = 180, message = "The lat should be between -180 and 180")
    private double lon;
}

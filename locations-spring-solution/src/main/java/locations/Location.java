package locations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    private long id;
    private String name;
    private double lat;
    private double lon;

    public Location(long id, String name) {
        this.id = id;
        this.name = name;
    }
}

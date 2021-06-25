package locations;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LocationTestJUnit4 {

    LocationParser locationParser = new LocationParser();

    @Test
    public void testParse() {
        Location location = locationParser.parse("Budapest,47.497912,19.040235");

        assertEquals("Budapest", location.getName());
    }
}

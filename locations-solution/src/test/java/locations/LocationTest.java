package locations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class LocationTest {

    LocationParser locationParser = new LocationParser();

    @Test
    void testParse() {
        Location location = locationParser.parse("Budapest,47.497912,19.040235");

        assertEquals("Budapest", location.getName());
        assertEquals(19.040235, location.getLon());
    }

    @Test
    void testParseWithEmptyOrNull() {
        assertThrows(IllegalArgumentException.class, () -> locationParser.parse(""));
        assertThrows(IllegalArgumentException.class, () -> locationParser.parse(null));
    }

    @Test
    void testParseWithWrongCoordinatesFormat() {
        assertThrows(NumberFormatException.class, () -> locationParser.parse("Budapest,xyz,19.040235"));
        assertThrows(NumberFormatException.class, () -> locationParser.parse("Budapest,47.497912,xy"));
    }
}

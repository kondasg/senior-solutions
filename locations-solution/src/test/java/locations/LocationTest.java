package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class LocationTest {

    LocationParser locationParser;

    @BeforeEach
    void init() {
        locationParser = new LocationParser();
    }

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

    @Test
    void testIsOnEquator() {
        assertTrue(locationParser.isOnEquator(new Location("Budapest", 0, 19.040235)));
        assertFalse(locationParser.isOnEquator(new Location("Budapest", 47.497912, 19.040235)));
    }

    @Test
    void testIsOnPrimeMeridian() {
        assertTrue(locationParser.isOnPrimeMeridian(new Location("Budapest", 47.497912, 0)));
        assertFalse(locationParser.isOnPrimeMeridian(new Location("Budapest", 47.497912, 19.040235)));
    }
}

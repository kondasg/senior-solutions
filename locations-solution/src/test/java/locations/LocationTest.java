package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class LocationTest {

    LocationParser locationParser;

    @BeforeEach
    void init() {
        locationParser = new LocationParser();
    }

    @Test
    @DisplayName("Test parse method")
    void testParse() {
        Location location = locationParser.parse("Budapest,47.497912,19.040235");

        assertEquals("Budapest", location.getName());
        assertEquals(19.040235, location.getLon());
    }

    @Test
    @DisplayName("Test the parse method input string of empty or null")
    void testParseWithEmptyOrNull() {
        assertThrows(IllegalArgumentException.class, () -> locationParser.parse(""));
        assertThrows(IllegalArgumentException.class, () -> locationParser.parse(null));
    }

    @Test
    @DisplayName("Test the parse method input string coordinates")
    void testParseWithWrongCoordinatesFormat() {
        assertThrows(NumberFormatException.class, () -> locationParser.parse("Budapest,xyz,19.040235"));
        assertThrows(NumberFormatException.class, () -> locationParser.parse("Budapest,47.497912,xy"));
    }

    @Test
    @DisplayName("Test that the point is on the equator\n")
    void testIsOnEquator() {
        assertTrue(locationParser.isOnEquator(new Location("Budapest", 0, 19.040235)));
        assertFalse(locationParser.isOnEquator(new Location("Budapest", 47.497912, 19.040235)));
    }

    @Test
    @DisplayName("Test that the point is on the starting meridian")
    void testIsOnPrimeMeridian() {
        assertTrue(locationParser.isOnPrimeMeridian(new Location("Budapest", 47.497912, 0)));
        assertFalse(locationParser.isOnPrimeMeridian(new Location("Budapest", 47.497912, 19.040235)));
    }
}

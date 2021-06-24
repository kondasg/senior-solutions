package locations;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

import static org.junit.jupiter.api.Assertions.*;

public class LocationRepeatedTest {

    LocationParser locationParser = new LocationParser();

    private final Object[][] locations = {
            {new Location("p1", 47.497912, 19.040235), false},
            {new Location("p2", 47.497912, 19.040235), false},
            {new Location("p3", 0, 19.040235), true},
            {new Location("p4", 47.497912, 19.040235), false},
            {new Location("p5", 0, 21), true},
            {new Location("p6", 47.497912, 19.040235), false},
    };

    @RepeatedTest(value = 6, name = "Get the point is on the equator {currentRepetition}/{totalRepetitions}")
    void testIsOnEquator(RepetitionInfo repetitionInfo) {
        assertEquals(locations[repetitionInfo.getCurrentRepetition() - 1][1],
                locationParser.isOnEquator((Location) locations[repetitionInfo.getCurrentRepetition() - 1][0]));
    }
}

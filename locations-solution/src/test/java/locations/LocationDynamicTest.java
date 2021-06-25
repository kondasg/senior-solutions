package locations;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class LocationDynamicTest {

    LocationParser locationParser = new LocationParser();

    @TestFactory
    Stream<DynamicTest> testIsOnEquator() {
        return Stream.of(new Object[][]{
                {new Location("p1", 47.497912, 19.040235), false},
                {new Location("p2", 47.497912, 19.040235), false},
                {new Location("p3", 0, 19.040235), true},
                {new Location("p4", 47.497912, 19.040235), false},
                {new Location("p5", 0, 21), true},
                {new Location("p6", 47.497912, 19.040235), false}
        })
                .map(item -> dynamicTest("Teszt " + item[1],
                        () -> assertEquals(item[1], locationParser.isOnEquator((Location) item[0]))));
    }
}

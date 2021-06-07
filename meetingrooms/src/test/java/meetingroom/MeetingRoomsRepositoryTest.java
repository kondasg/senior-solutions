package meetingroom;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class MeetingRoomsRepositoryTest {

    //private final InMemoryMeetingRoomsRepository repository = new InMemoryMeetingRoomsRepository();
    private final MariaDbMeetingRoomsRepository repository = new MariaDbMeetingRoomsRepository();

    @BeforeEach
    void init() {
        repository.deleteAll();
        repository.save("Róma", 5, 6);
        repository.save("Párizs", 10, 10);
        repository.save("London", 2, 3);
        repository.save("Budapest", 20, 11);
        repository.save("Bécs", 3, 4);
    }

    @Test
    void saveTest() {
        repository.save("Berlin", 3, 9);
        assertEquals(6, repository.list().size());
    }

    @Test
    void listTest() {
        assertEquals(5, repository.list().size());
        assertEquals("Bécs", repository.list().get(0).getName());
    }

    @Test
    void listReverseTest() {
        assertEquals("Bécs", repository.listReverse().get(4).getName());
    }

    @Test
    void listEverySecondTest() {
        List<MeetingRoom> meetingRooms = repository.listEverySecond();
        assertEquals(2, meetingRooms.size());
        assertEquals("Párizs", meetingRooms.get(1).getName());
    }

    @Test
    void getAreasTest() {
        List<MeetingRoom> meetingRooms = repository.getAreas();
        assertEquals(220, meetingRooms.get(0).getArea());
        assertEquals("London", meetingRooms.get(4).getName());
    }

    @Test
    void findByNameTest() {
        assertEquals("London", repository.findByName("London").getName());
        assertNull(repository.findByName("Lond"));
    }

    @Test
    void findByPatternTest() {
        repository.save("Malibu", 1, 2);
        repository.save("Bukarest", 3, 4);
        List<MeetingRoom> meetingRooms = repository.findByPattern("bu");
        assertEquals(3, meetingRooms.size());
        assertEquals("Bukarest", meetingRooms.get(1).getName());
    }

    @Test
    void findByArea() {
        assertEquals(3, repository.findByArea(20).size());
    }
}
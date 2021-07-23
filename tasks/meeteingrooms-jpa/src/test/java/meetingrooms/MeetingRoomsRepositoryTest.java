package meetingrooms;

import meetingroom.JpaMeetingRoomsRepository;
import meetingroom.MeetingRoom;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MeetingRoomsRepositoryTest {

    private JpaMeetingRoomsRepository repository;
    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        repository = new JpaMeetingRoomsRepository(entityManagerFactory);

        repository.save("Párizs", 10, 10);
        repository.save("Róma", 5, 6);
        repository.save("London", 2, 3);
        repository.save("Budapest", 20, 11);
        repository.save("Bécs", 3, 4);
    }

    @AfterEach
    void end() {
        entityManagerFactory.close();
    }

    @Test
    void saveTest() {
        repository.save("Berlin", 3, 9);
        List<MeetingRoom> meetingRooms = repository.list();
        assertEquals(6, meetingRooms.size());
        assertEquals("Bécs", meetingRooms.get(0).getName());
    }

    @Test
    void listReverseTest() {
        List<MeetingRoom> meetingRooms = repository.listReverse();
        assertEquals(5, meetingRooms.size());
        assertEquals("Róma", meetingRooms.get(0).getName());
    }

    @Test
    void listEverySecondTest() {
        List<MeetingRoom> meetingRooms = repository.listEverySecond();
        assertEquals(2, meetingRooms.size());
        assertEquals("Budapest", meetingRooms.get(0).getName());
    }

    @Test
    void findByNameTest() {
        MeetingRoom meetingRoom = repository.findByName("Róma");
        assertEquals(5, meetingRoom.getWidth());
        assertEquals(6, meetingRoom.getLength());
    }

    @Test
    void findByPatternTest() {
        List<MeetingRoom> meetingRooms = repository.findByPattern("nd");
        assertEquals(1, meetingRooms.size());
        assertEquals("London", meetingRooms.get(0).getName());
    }

}

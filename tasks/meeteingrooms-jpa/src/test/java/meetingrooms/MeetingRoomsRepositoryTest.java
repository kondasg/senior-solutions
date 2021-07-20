package meetingrooms;

import meetingroom.JpaMeetingRoomsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MeetingRoomsRepositoryTest {

    private JpaMeetingRoomsRepository repository;

    @BeforeEach
    void init() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        repository = new JpaMeetingRoomsRepository(entityManagerFactory);

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
}

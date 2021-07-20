package meetingroom;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class JpaMeetingRoomsRepository implements MeetingRoomsRepository {

    private EntityManagerFactory entityManagerFactory;

    public JpaMeetingRoomsRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void save(String name, int width, int length) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(new MeetingRoom(name, width, length));
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<MeetingRoom> list() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<MeetingRoom> meetingRooms = entityManager.createQuery("SELECT m FROM MeetingRoom m ORDER BY m.name",
                MeetingRoom.class).getResultList();
        entityManager.close();
        return meetingRooms;
    }

    @Override
    public List<MeetingRoom> listReverse() {
        return null;
    }

    @Override
    public List<MeetingRoom> listEverySecond() {
        return null;
    }

    @Override
    public List<MeetingRoom> getAreas() {
        return null;
    }

    @Override
    public MeetingRoom findByName(String name) {
        return null;
    }

    @Override
    public List<MeetingRoom> findByPattern(String pattern) {
        return null;
    }

    @Override
    public List<MeetingRoom> findByArea(int area) {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}

package meetingroom;

import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.stream.IntStream;

@AllArgsConstructor
public class JpaMeetingRoomsRepository implements MeetingRoomsRepository {

    private EntityManagerFactory entityManagerFactory;

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
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<MeetingRoom> meetingRooms = entityManager.createQuery("SELECT m FROM MeetingRoom m ORDER BY m.name DESC",
                MeetingRoom.class).getResultList();
        entityManager.close();
        return meetingRooms;
    }

    @Override
    public List<MeetingRoom> listEverySecond() {
        List<MeetingRoom> meetingRooms = list();

        return IntStream.range(0, meetingRooms.size())
                .filter(i -> i % 2 != 0)
                .mapToObj(meetingRooms::get)
                .toList();
    }

    @Override
    public MeetingRoom findByName(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        MeetingRoom meetingRoom =
                entityManager.createQuery("SELECT m FROM MeetingRoom m WHERE m.name LIKE :name", MeetingRoom.class)
                .setParameter("name", name)
                .getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return meetingRoom;
    }

    @Override
    public List<MeetingRoom> findByPattern(String pattern) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<MeetingRoom> meetingRooms =
                entityManager.createQuery("SELECT m FROM MeetingRoom m WHERE m.name LIKE :pattern", MeetingRoom.class)
                        .setParameter("pattern", "%" + pattern + "%")
                        .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return meetingRooms;
    }

    @Override
    public void deleteAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM MeetingRoom").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}

package activitytracker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class ActivityTrackerMain {

    public static void main(String[] args) {
        List<Activity> activities = List.of(
                new Activity(LocalDateTime.of(2000, 1, 1, 10, 0, 0),
                        "Biciklizés újév napján", Activity.ActivityType.BIKING),
                new Activity(LocalDateTime.of(2000, 1, 2, 15, 40, 0),
                        "Hegymászás", Activity.ActivityType.HIKING),
                new Activity(LocalDateTime.of(2000, 1, 3, 6, 0, 0),
                        "Futás 10km", Activity.ActivityType.RUNNING)
        );

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");;
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        insertActivity(activities, entityManager);

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    private static void insertActivity(List<Activity> activities, EntityManager entityManager) {
        for (Activity activity : activities) {
            entityManager.persist(activity);
        }
    }


}

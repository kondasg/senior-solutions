package activitytracker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ActivityDao {

    private EntityManagerFactory entityManagerFactory;

    public ActivityDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void saveActivity(Activity activity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(activity);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Activity findActivityById(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Activity activity = entityManager.find(Activity.class, id);
        entityManager.close();
        return activity;
    }

    public List<Activity> listActivities() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Activity> activities = entityManager.createQuery("SELECT a FROM Activity a ORDER BY a.startTime",
                Activity.class).getResultList();
        entityManager.close();
        return activities;
    }

    public Activity updateActivity(long id, String description) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Activity activity = entityManager.find(Activity.class, id);
        entityManager.getTransaction().begin();
        activity.setDesc(description);
        entityManager.getTransaction().commit();
        entityManager.close();
        return activity;
    }
}

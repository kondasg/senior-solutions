package activitytracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ActivityDaoTest {

    private ActivityDao activityDao;

    @BeforeEach
    void init() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        activityDao = new ActivityDao(entityManagerFactory);
    }

    @Test
    void saveActivityTest() {
        activityDao.saveActivity(
                new Activity(LocalDateTime.of(2000, 1, 1, 10, 0, 0),
                        "Biciklizés újév napján", Activity.ActivityType.BIKING)
        );

        List<Activity> activities = activityDao.listActivities();
        assertEquals(1, activities.size());
        assertEquals(Activity.ActivityType.BIKING, activities.get(0).getType());
    }

    @Test
    void findActivityByIdTest() {
        activityDao.saveActivity(
                new Activity(LocalDateTime.of(2000, 1, 1, 10, 0, 0),
                        "Biciklizés újév napján", Activity.ActivityType.BIKING)
        );
        Activity activity = activityDao.findActivityById(1);
        assertEquals(Activity.ActivityType.BIKING, activity.getType());
    }

    @Test
    void listActivitiesTest() {
        activityDao.saveActivity(
                new Activity(LocalDateTime.of(2000, 1, 1, 10, 0, 0),
                        "Biciklizés újév napján", Activity.ActivityType.BIKING)
        );
        activityDao.saveActivity(
                new Activity(LocalDateTime.of(2000, 1, 1, 10, 0, 0),
                        "Biciklizés újév napján", Activity.ActivityType.BIKING)
        );
        activityDao.saveActivity(
                new Activity(LocalDateTime.of(2000, 1, 1, 10, 0, 0),
                        "Biciklizés újév napján", Activity.ActivityType.BIKING)
        );

        List<Activity> activities = activityDao.listActivities();
        assertEquals(3, activities.size());
    }

    @Test
    void updateActivityTest() {
        Activity activity = new Activity(LocalDateTime.of(2000, 1, 1, 10, 0, 0),
                "Biciklizés újév napján", Activity.ActivityType.BIKING);
        activityDao.saveActivity(activity);
        LocalDateTime createdAt = activity.getCreatedAt();
        long id = activity.getId();

        String modifyDescription = "Módosított szöveg";
        Activity activityUpdated = activityDao.updateActivity(id, modifyDescription);
        LocalDateTime updatedAt = activityUpdated.getUpdatedAt();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");

        Activity modActivity = activityDao.findActivityById(id);

        assertEquals(createdAt.format(formatter), modActivity.getCreatedAt().toString());
        assertEquals(modifyDescription, modActivity.getDesc());
        assertEquals(updatedAt.format(formatter), modActivity.getUpdatedAt().toString());
    }
}
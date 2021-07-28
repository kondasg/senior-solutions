package activitytracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
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

    @Test
    void findActivityByIdWithLabelsTest() {
        Activity activity = new Activity(LocalDateTime.of(2000, 1, 1, 10, 0, 0),
                "Biciklizés újév napján", Activity.ActivityType.BIKING);
        activity.setLabels(List.of("12%", "50km", "-10C"));
        activityDao.saveActivity(activity);

        Activity anotherActivity = activityDao.findActivityByIdWithLabels(activity.getId());
        assertEquals(List.of("12%", "50km", "-10C"), anotherActivity.getLabels());
    }

    @Test
    void findActivityByIdWithTrackPointsTest() {
        TrackPoint trackPoint1 = new TrackPoint(LocalDate.of(2000, 1, 1), 48.1, 19.2);
        TrackPoint trackPoint2 = new TrackPoint(LocalDate.of(2000, 1, 1), 48.2, 19.3);

        Activity activity = new Activity(LocalDateTime.of(2000, 1, 1, 10, 0, 0),
                "Biciklizés újév napján", Activity.ActivityType.BIKING);
        activity.addTrackPoint(trackPoint1);
        activity.addTrackPoint(trackPoint2);
        activityDao.saveActivity(activity);

        Activity anotherActivity = activityDao.findActivityByIdWithTrackPoints(activity.getId());
        assertEquals(2, anotherActivity.getTrackPoints().size());
    }
}
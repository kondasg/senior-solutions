package activitytracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AeaDaoTest {

    private AreaDao areaDao;
    private ActivityDao activityDao;

    @BeforeEach
    void init() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        areaDao = new AreaDao(entityManagerFactory);
        activityDao = new ActivityDao(entityManagerFactory);
    }

    @Test
    void saveAreaTest() {
        Activity activity1 = new Activity(LocalDateTime.of(2000, 1, 1, 10, 0, 0),
                "1.", Activity.ActivityType.BIKING);
        Activity activity2 = new Activity(LocalDateTime.of(2000, 1, 2, 8, 30, 0),
                "2.", Activity.ActivityType.BIKING);
        Activity activity3 = new Activity(LocalDateTime.of(2000, 1, 3, 12, 0, 0),
                "3.", Activity.ActivityType.BIKING);

        Area area1 = new Area("Bükk");
        Area area2 = new Area("Mátra");
        Area area3 = new Area("Cserehát");

        activityDao.saveActivity(activity1);
        activityDao.saveActivity(activity2);
        activityDao.saveActivity(activity3);

        area1.addActivity(activity1);
        area1.addActivity(activity3);

        area2.addActivity(activity2);

        area3.addActivity(activity2);
        area3.addActivity(activity3);

        areaDao.saveArea(area1);
        areaDao.saveArea(area2);
        areaDao.saveArea(area3);

        Area area = areaDao.findAreaByName("Bükk");

        assertEquals(Set.of("1.", "3."), area.getActivities().stream().map(Activity::getDesc).collect(Collectors.toSet()));
    }
}

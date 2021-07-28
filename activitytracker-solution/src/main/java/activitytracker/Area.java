package activitytracker;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @ManyToMany
    private Set<Activity> activities = new HashSet<>();

    public Area() {
    }

    public Area(String name) {
        this.name = name;
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
        activity.getAreas().add(this);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }
}

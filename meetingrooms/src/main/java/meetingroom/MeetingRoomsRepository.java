package meetingroom;

import java.util.List;

public interface MeetingRoomsRepository {

    void save(String name, int width, int length);

    List<MeetingRoom> list();

    List<MeetingRoom> listReverse();

    List<MeetingRoom> listEverySecond();

    List<MeetingRoom> getAreas();

    MeetingRoom findByName(String name);

    List<MeetingRoom> findByPattern(String pattern);

    List<MeetingRoom> findByArea(int area);

    void deleteAll();
}

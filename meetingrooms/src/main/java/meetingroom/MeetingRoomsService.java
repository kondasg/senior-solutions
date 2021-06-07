package meetingroom;

import java.util.List;

public class MeetingRoomsService {

    private final MeetingRoomsRepository meetingRoomsRepository;

    public MeetingRoomsService(MeetingRoomsRepository meetingRoomsRepository) {
        this.meetingRoomsRepository = meetingRoomsRepository;
    }

    public void save(String name, int width, int length) {
        meetingRoomsRepository.save(name, width, length);
    }

    public List<MeetingRoom> list() {
        return meetingRoomsRepository.list();
    }

    public List<MeetingRoom> listReverse() {
        return meetingRoomsRepository.listReverse();
    }

    public List<MeetingRoom> listEverySecond() {
        return meetingRoomsRepository.listEverySecond();
    }

    public List<MeetingRoom> getAreas() {
        return meetingRoomsRepository.getAreas();
    }

    public MeetingRoom findByName(String name) {
        return meetingRoomsRepository.findByName(name);
    }

    public List<MeetingRoom> findByPattern(String pattern) {
        return meetingRoomsRepository.findByPattern(pattern);
    }

    public List<MeetingRoom> findByArea(int area) {
        return meetingRoomsRepository.findByArea(area);
    }
}

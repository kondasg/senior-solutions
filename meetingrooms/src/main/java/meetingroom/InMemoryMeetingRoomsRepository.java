package meetingroom;

import java.text.Collator;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InMemoryMeetingRoomsRepository implements MeetingRoomsRepository {

    private final List<MeetingRoom> meetingRooms = new ArrayList<>();

    @Override
    public void save(String name, int width, int length) {
        meetingRooms.add(new MeetingRoom(name, width, length));
    }

    @Override
    public List<MeetingRoom> list() {
        return meetingRooms.stream()
                .sorted(Comparator.comparing(MeetingRoom::getName,
                        Collator.getInstance(new Locale("hu", "HU"))))
                .collect(Collectors.toList());
    }

    @Override
    public List<MeetingRoom> listReverse() {
        return meetingRooms.stream()
                .sorted(Comparator.comparing(MeetingRoom::getName,
                        Collator.getInstance(new Locale("hu", "HU"))).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<MeetingRoom> listEverySecond() {
        return IntStream.range(0, list().size())
                .filter(i -> i % 2 != 0)
                .mapToObj(list()::get)
                .collect(Collectors.toList());
    }

    @Override
    public List<MeetingRoom> getAreas() {
        return meetingRooms.stream()
                .sorted(Comparator.comparing(MeetingRoom::getArea).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public MeetingRoom findByName(String name) {
        return meetingRooms.stream()
                .filter(n -> n.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<MeetingRoom> findByPattern(String pattern) {
        return list().stream()
                .filter(p -> p.getName().toLowerCase().contains(pattern.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<MeetingRoom> findByArea(int area) {
        return list().stream()
                .filter(a -> a.getArea() > area)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAll() {
        meetingRooms.clear();
    }
}

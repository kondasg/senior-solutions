package meetingroom;

public class MeetingRoom {

    private long id;
    private String name;
    private int width;
    private int length;

    public MeetingRoom() {
    }

    public MeetingRoom(String name, int width, int length) {
        this.name = name;
        this.width = width;
        this.length = length;
    }

    public MeetingRoom(long id, String name, int width, int length) {
        this.id = id;
        this.name = name;
        this.width = width;
        this.length = length;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public int getArea() {
        return width * length;
    }

    @Override
    public String toString() {
        return "Name: " + name + " , Width: " + width + " m, Length: " + length + " m, Area: " + getArea() + " m2";
    }
}

package meetingroom;

import java.util.Scanner;

public class MeetingRoomsController {

    private final Scanner scanner = new Scanner(System.in);

    private final MeetingRoomsService meetingRoomsService =
            new MeetingRoomsService(new MariaDbMeetingRoomsRepository());

//    private final MeetingRoomsService meetingRoomsService =
//            new MeetingRoomsService(new InMemoryMeetingRoomsRepository());

    public static final String[] MENU = {
            "0. Tárgyaló rögzítése",
            "1. Tárgyalók névsorrendben",
            "2. Tárgyalók név alapján visszafele sorrendben",
            "3. Minden második tárgyaló",
            "4. Területek",
            "5. Keresés pontos név alapján",
            "6. Keresés névtöredék alapján",
            "7. Keresés terület alapján",
            "99. Kilépés"
    };

    public static void main(String[] args) {
        new MeetingRoomsController().printMenu();
    }

    public void printMenu() {
        System.out.println("\nMENÜ");
        for (String item : MENU) {
            System.out.println(item);
        }
        int selectedMenu = -1;
        try {
            String menu = inputText("\nKérem válasszon a menüpontok közül: ");
            selectedMenu = Integer.parseInt(menu);
        } catch (NumberFormatException ne) {
            printMenu();
        }

        selectMenu(selectedMenu);
        printMenu();
    }

    private void selectMenu(int selectedMenu) {
        switch (selectedMenu) {
            case 99 -> exit();
            case 0 -> saveMeetingRoom();
            case 1 -> listOfMeetingRooms();
            case 2 -> listOfMeetingRoomsInReverse();
            case 3 -> everySecondtOfListOfMeetingRooms();
            case 4 -> getAreas();
            case 5 -> findByName();
            case 6 -> findByPattern();
            case 7 -> findByArea();
            default -> printMenu();
        }
    }

    public void saveMeetingRoom() {
        System.out.println("Tárgyaló rögzítése");
        String name = inputText("Tárgyaló neve: ");
        String width = inputText("Tárgyaló szélessége: ");
        String length = inputText("Tárgyaló hosszúsága: ");
        try {
            meetingRoomsService.save(name, Integer.parseInt(width), Integer.parseInt(length));
        } catch (NumberFormatException ne) {
            System.out.println("NumberFormatException: szélessége or hosszúsága");
            saveMeetingRoom();
        }
        System.out.println("Tárgyaló mentése sikeres!");
    }

    public void listOfMeetingRooms() {
        System.out.println("Tárgyalók névsorrendben");
        for (MeetingRoom meetingRoom : meetingRoomsService.list()) {
            System.out.println(meetingRoom.getName());
        }
    }

    public void listOfMeetingRoomsInReverse() {
        System.out.println("Tárgyalók név alapján visszafele sorrendben");
        for (MeetingRoom meetingRoom : meetingRoomsService.listReverse()) {
            System.out.println(meetingRoom.getName());
        }
    }

    public void everySecondtOfListOfMeetingRooms() {
        System.out.println("Minden második tárgyaló");
        for (MeetingRoom meetingRoom : meetingRoomsService.listEverySecond()) {
            System.out.println(meetingRoom.getName());
        }
    }

    public void getAreas() {
        System.out.println("Területek");
        for (MeetingRoom meetingRoom : meetingRoomsService.getAreas()) {
            System.out.println(meetingRoom.toString());
        }
    }

    public void findByName() {
        System.out.println("Keresés pontos név alapján");
        String name = inputText("Tárgyaló neve: ");
        MeetingRoom meetingRoom = meetingRoomsService.findByName(name);
        if (meetingRoom != null) {
            System.out.println(meetingRoom.toString());
        }
    }

    public void findByPattern() {
        System.out.println("Keresés névtöredék alapján");
        String pattern = inputText("Tárgyaló névtöredék: ");
        for (MeetingRoom meetingRoom : meetingRoomsService.findByPattern(pattern)) {
            System.out.println(meetingRoom.toString());
        }
    }

    public void findByArea() {
        System.out.println("Keresés terület alapján");
        int area = Integer.MIN_VALUE;
        try {
            area = Integer.parseInt(inputText("Terület: "));
        } catch (NumberFormatException ne) {
            System.out.println("NumberFormatException: terület");
            findByArea();
        }
        for (MeetingRoom meetingRoom : meetingRoomsService.findByArea(area)) {
            System.out.println(meetingRoom.toString());
        }
    }

    private void exit() {
        System.exit(0);
    }

    private String inputText(String text) {
        System.out.print(text);
        return scanner.nextLine();
    }
}

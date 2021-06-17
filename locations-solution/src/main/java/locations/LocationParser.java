package locations;

public class LocationParser {

    public Location parse(String text) {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Üres bemeneti paraméter!");
        }
        String[] splittedText = text.split(",");
        try {
            return new Location(splittedText[0], Double.parseDouble(splittedText[1]), Double.parseDouble(splittedText[2]));
        } catch (NumberFormatException ne) {
            throw new NumberFormatException("Nem megfelelő lat vagy lon formátum");
        }
    }

    public boolean isOnEquator(Location location) {
        return location.getLat() == 0;
    }

    public boolean isOnPrimeMeridian(Location location) {
        return location.getLon() == 0;
    }
}

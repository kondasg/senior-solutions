package locations;

public class Location {

    private String name;
    private double lat;
    private double lon;

    public Location(String name, double lat, double lon) {
        if (!isValidLatLon(lat, lon)) {
            throw new IllegalArgumentException("Nem megfelelő lat vagy lon értékek!");
        }
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    private boolean isValidLatLon(double lat, double lon) {
        return (-90 < lat && 90 > lat && -180 < lon && 180 > lon);
    }

    public double distanceFrom(Location location) {
        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(location.lat - this.lat);
        double lonDistance = Math.toRadians(location.lon - this.lon);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(this.lat)) * Math.cos(Math.toRadians(location.lat))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return Math.sqrt(Math.pow(R * c, 2));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}

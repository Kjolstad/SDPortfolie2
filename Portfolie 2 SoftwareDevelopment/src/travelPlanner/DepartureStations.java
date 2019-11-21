package travelPlanner;

public enum DepartureStations {
    København , HøjeTaastrup, Roskilde, Ringsted, Odense, Næstved, NykøbingFalster;

    private DepartureStations(){}

    public String value() {
        return name();

    }

    public static DepartureStations fromvalue(String v) {
        return valueOf(v);
    }
}
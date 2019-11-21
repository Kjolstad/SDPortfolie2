package travelPlanner;

public enum ArrivalStations {
    København , HøjeTaastrup, Roskilde, Ringsted, Odense, Næstved, NykøbingFalster;

    private ArrivalStations(){}

    public String value() {
        return name();

    }

    public static ArrivalStations fromvalue(String v) {
        return valueOf(v);
    }
}
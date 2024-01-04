import java.util.Arrays;

public class Incident implements Comparable<Incident> {
    private String dateTime;
    private String neighborhood;
    private DayOfWeekEnum dayOfWeek;

// Link to data set: "https://data.sfgov.org/Public-Safety/Map-of-Police-Department-Incident-Reports-2018-to-/jq29-s5wp"
   
    public Incident(String dateTime, String dayOfWeek, String neighborhood) {
        this.dateTime = dateTime;
        this.dayOfWeek = DayOfWeekEnum.fromString(dayOfWeek);
        this.neighborhood = neighborhood;
    }

    public enum DayOfWeekEnum {
        SUNDAY("Sunday"),
        MONDAY("Monday"),
        TUESDAY("Tuesday"),
        WEDNESDAY("Wednesday"),
        THURSDAY("Thursday"),
        FRIDAY("Friday"),
        SATURDAY("Saturday");

        private String displayName;

        DayOfWeekEnum(String displayName) {
            this.displayName = displayName;
        }

        String getDisplayName() {
            return displayName;
        }

        public static DayOfWeekEnum fromString(String dayOfWeekString) {
            for (DayOfWeekEnum dayOfWeek : values()) {
                if (dayOfWeek.displayName.equalsIgnoreCase(dayOfWeekString)) {
                    return dayOfWeek;
                }
            }
            throw new IllegalArgumentException(dayOfWeekString + " is not a valid day of the week.");
        }

        @Override
        public String toString() {
            return displayName;
        }

        public static String[] getDisplayNames() {
            return Arrays.stream(values()).map(DayOfWeekEnum::getDisplayName).toArray(String[]::new);
        }
    }

    
    public String getDateTime() {
        return dateTime;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getYear() {
        return dateTime.substring(dateTime.lastIndexOf('/') + 1, dateTime.lastIndexOf('/') + 5);
    }
    
    public DayOfWeekEnum getDayOfWeek() {
        return dayOfWeek;
    }
    
    
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }


    
    @Override
    public String toString() {
        return "Date and Time of Incident = " + dateTime + '\n' +
                "Day of Week = " + dayOfWeek.getDisplayName() + '\n' +
                "Neighborhood = " + neighborhood + '\n';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Incident incident = (Incident) obj;
        return dateTime.equals(incident.dateTime) && neighborhood.equals(incident.neighborhood);
    }

    @Override
    public int compareTo(Incident other) {
        return dateTime.compareTo(other.dateTime);
    }
}

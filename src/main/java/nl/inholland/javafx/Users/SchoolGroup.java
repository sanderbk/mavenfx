package nl.inholland.javafx.Users;

public enum SchoolGroup {
    INF02A("INF02-A"),
    INF02B("INF02-B"),
    INF02C("INF02-C");

    private String displayName;

    SchoolGroup(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() { return displayName; }

    // Optionally and/or additionally, toString.
    @Override public String toString() { return displayName; }
    }



package models;

public interface User {
    String name();
    String id();

    default String displayInfo() {
        return String.format("Name: %s, ID: %s", name(), id());
    }
}

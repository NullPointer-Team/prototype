package com.game;

public enum Room {
    DINING_ROOM("Dining Room"),
    KITCHEN("Kitchen"),
    GARDEN("Garden"),
    HALL("Hall");


    private String name;

    Room(final String name) {
        this.name = name;
    }

    public String room() { return name; }

    public static Room fromString(String search) {
        for (Room place: Room.values()) {
            if (place.room().equals(search)) {
                return place;
            }
        }
        return null;
    }
}

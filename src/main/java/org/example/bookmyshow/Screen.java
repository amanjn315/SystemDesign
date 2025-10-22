package org.example.bookmyshow;

import java.util.List;

/**
 * @author amanjain
 **/
public class Screen {
    private final String screenId;
    private final String name;
    private final List<Seat> seats;

    public Screen(String screenId, String name, List<Seat> seats) {
        this.screenId = screenId;
        this.name = name;
        this.seats = seats;
    }

    public List<Seat> getSeats() {
        return this.seats;
    }
}

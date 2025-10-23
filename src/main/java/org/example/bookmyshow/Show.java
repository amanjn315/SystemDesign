package org.example.bookmyshow;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author amanjain
 **/
public class Show {
    private final String showId;
    private final Movie movie;
    private final Screen screen;
    private final LocalDateTime startTime;

    private final List<ShowSeat> showSeats;

    public Show(String showId, Movie movie, Screen screen, LocalDateTime startTime) {
        this.showId = showId;
        this.movie = movie;
        this.screen = screen;
        this.startTime = startTime;
        this.showSeats = new ArrayList<>();
        for(Seat seat : screen.getSeats()){
            showSeats.add(new ShowSeat(seat.getSeatId(), seat, new BigDecimal(500)));
        }
    }

    public Movie getMovie() {
        return movie;
    }

    public Screen getScreen() {
        return screen;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public List<ShowSeat> getShowSeats() {
        return this.showSeats;
    }

    public ShowSeat findSeat(String showSeatId) {
        for(ShowSeat showSeat : showSeats){
            if(showSeat.getShowSeatId().equals(showSeatId)){
                return showSeat;
            }
        }
        return null;
    }
}

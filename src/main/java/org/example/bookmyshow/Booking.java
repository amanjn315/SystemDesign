package org.example.bookmyshow;

import java.util.List;

/**
 * @author amanjain
 **/
public class Booking {
    private final String bookingId;
    private final String userId;
    private final Show show;
    private final List<ShowSeat> showSeats;
    private BookingStatus bookingStatus;

    public Booking(String bookingId, String userId, Show show, List<ShowSeat> showSeats) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.show = show;
        this.showSeats = showSeats;
        this.bookingStatus = BookingStatus.PENDING;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getUserId(){
        return this.userId;
    }

    public List<ShowSeat> getShowSeats(){
        return this.showSeats;
    }
}

package org.example.bookmyshow;

/**
 * @author amanjain
 **/
public class Seat {
    private final String seatId;
    private final int rowNumber;
    private final SeatType seatType;

    public Seat(String seatId, int rowNumber, SeatType seatType) {
        this.seatId = seatId;
        this.rowNumber = rowNumber;
        this.seatType = seatType;
    }

    public String getSeatId() {
        return seatId;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public SeatType getSeatType() {
        return seatType;
    }
}

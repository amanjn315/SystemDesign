package org.example.bookmyshow;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author amanjain
 **/
public class ShowSeat {
    private final String showSeatId;
    private final Seat seat;
    private final BigDecimal price;

    private SeatStatus status;
    private LocalDateTime lockExpiryTime;
    private String lockedByUserId;

    public ShowSeat(String showSeatId, Seat seat, BigDecimal price) {
        this.showSeatId = showSeatId;
        this.seat = seat;
        this.price = price;
        this.status = SeatStatus.AVAILABLE; // Default to AVAILABLE
    }

    public Seat getSeat() {
        return seat;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getShowSeatId(){
        return this.showSeatId;
    }

    public SeatStatus getStatus() {
        // We must check if a lock is expired *before* returning the status
        if (this.status == SeatStatus.LOCKED && LocalDateTime.now().isAfter(this.lockExpiryTime)) {
            // The lock has expired! Automatically release it.
            this.releaseLock();
        }
        return this.status;
    }

    public synchronized boolean lockSeat(String userId, int lockDurationMinutes) {
        // Check current status (getStatus() also handles expired lock cleanup)
        if (getStatus() == SeatStatus.AVAILABLE) {
            // Seat is free, lock it
            this.status = SeatStatus.LOCKED;
            this.lockExpiryTime = LocalDateTime.now().plusMinutes(lockDurationMinutes);
            this.lockedByUserId = userId;
            return true;
        }
        // Seat is not available (either BOOKED or LOCKED by someone else)
        return false;
    }

    public synchronized boolean confirmSeat(String userId) {
        // Check if the seat is currently locked *by this specific user*
        if (getStatus() == SeatStatus.LOCKED && this.lockedByUserId.equals(userId)) {
            // Correct user, confirm the booking
            this.status = SeatStatus.BOOKED;
            this.lockExpiryTime = null; // Clear lock info
            this.lockedByUserId = null;
            return true;
        }
        // Seat was not locked by this user (or lock expired)
        return false;
    }

    public synchronized void releaseLock() {
        // Only release if it's currently LOCKED (not if it's BOOKED)
        if (this.status == SeatStatus.LOCKED) {
            this.status = SeatStatus.AVAILABLE;
            this.lockExpiryTime = null;
            this.lockedByUserId = null;
        }
    }
}

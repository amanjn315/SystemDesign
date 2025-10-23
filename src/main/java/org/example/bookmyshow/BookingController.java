package org.example.bookmyshow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author amanjain
 **/
public class BookingController {
    private final ShowService showService;
    private final Map<String, Booking> bookingDatabase;
    private static final int LOCK_DURATION_MINUTES = 10;

    public BookingController(ShowService showService, Map<String, Booking> bookingDatabase) {
        this.showService = showService;
        this.bookingDatabase = bookingDatabase;
    }

    public List<ShowSeat> getAvailableShowSeats(String showId) {
        Show show = showService.getShow(showId);
        if (show == null) {
            throw new IllegalArgumentException("Show not found with ID: " + showId);
        }

        return show.getShowSeats().stream()
                .filter(seat -> seat.getStatus() == SeatStatus.AVAILABLE)
                .collect(Collectors.toList());
    }

    public Booking createBooking(String userId, String showId, List<String> showSeatIds) {
        Show show = showService.getShow(showId);
        if (show == null) {
            throw new IllegalArgumentException("Show not found.");
        }

        // 1. Find all the ShowSeat objects from the IDs
        // Using a Map for O(1) lookups would be faster than this O(N*M) loop
        List<ShowSeat> requestedSeats = new ArrayList<>();
        for (String seatId : showSeatIds) {
            ShowSeat seat = show.findSeat(seatId); // Assumes Show has a helper findSeat()
            if (seat == null) {
                throw new IllegalArgumentException("Seat not found: " + seatId);
            }
            requestedSeats.add(seat);
        }

        // 2. --- Transaction Start ---
        // This is the critical part. We must lock all seats or none.
        List<ShowSeat> lockedSeats = new ArrayList<>();
        boolean allLocksAcquired = true;

        for (ShowSeat seat : requestedSeats) {
            if (seat.lockSeat(userId, LOCK_DURATION_MINUTES)) {
                // Lock was successful, add to our list
                lockedSeats.add(seat);
            } else {
                // This seat couldn't be locked (it was taken by someone else)
                allLocksAcquired = false;
                break; // Stop trying to lock more seats
            }
        }

        // 3. --- Transaction Rollback (if failed) ---
        if (!allLocksAcquired) {
            // A lock failed. We must release all the locks we just acquired.
            for (ShowSeat seatToRelease : lockedSeats) {
                seatToRelease.releaseLock();
            }
            // Throw an exception or return null to signal failure
            throw new RuntimeException("Seats not available. Please try again.");
        }

        // 4. --- Transaction Commit (if successful) ---
        // All locks acquired! Create the booking.
        String bookingId = UUID.randomUUID().toString();
        Booking newBooking = new Booking(bookingId, userId, show, lockedSeats);

        // Save the booking (so we can find it later for confirmation)
        bookingDatabase.put(bookingId, newBooking);

        System.out.println("Booking created successfully (pending payment): " + bookingId);
        return newBooking;
    }

    public boolean confirmBooking(String bookingId, String userId) {
        Booking booking = bookingDatabase.get(bookingId);

        // Validations
        if (booking == null) {
            System.out.println("Booking not found.");
            return false;
        }
        if (!booking.getUserId().equals(userId)) {
            System.out.println("User mismatch.");
            return false;
        }
        if (booking.getBookingStatus() != BookingStatus.PENDING) {
            System.out.println("Booking is not in a pending state.");
            return false;
        }

        // --- Critical Step: Confirm all seats ---
        // We must hold a lock on the booking/show here
        for (ShowSeat seat : booking.getShowSeats()) {
            if (!seat.confirmSeat(userId)) {
                // This should *theoretically* never happen if our lock logic is correct
                // But it's a good safeguard.
                System.out.println("Failed to confirm seat: " + seat.getSeat().getSeatId());
                // We might need a complex rollback here, but for now, we'll fail.
                return false;
            }
        }

        // All seats confirmed, update booking status
        booking.setBookingStatus(BookingStatus.CONFIRMED);
        bookingDatabase.put(bookingId, booking); // Re-save the booking

        System.out.println("Booking confirmed: " + bookingId);
        return true;
    }
}

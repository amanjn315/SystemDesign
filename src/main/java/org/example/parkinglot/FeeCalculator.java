package org.example.parkinglot;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author amanjain
 **/
public class FeeCalculator {
    private static final BigDecimal RATE_PER_HOUR = new BigDecimal("10.00");

    public BigDecimal calculateFee(ParkingTicket ticket) {
        LocalDateTime entryTime = ticket.getEntryTime();
        LocalDateTime exitTime = ticket.getExitTime();
        long durationInHours = (Duration.between(entryTime, exitTime).getSeconds() + 3599) / 3600;
        return RATE_PER_HOUR.multiply(new BigDecimal(durationInHours));
    }
}

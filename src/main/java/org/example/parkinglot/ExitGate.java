package org.example.parkinglot;

import java.math.BigDecimal;

/**
 * @author amanjain
 **/
public class ExitGate extends Gate {
    private FeeCalculator feeCalculator;
    public ExitGate(ParkingLot parkingLot, FeeCalculator feeCalculator) {
        super(parkingLot);
        this.feeCalculator = feeCalculator;
    }

    public void processExit(ParkingTicket ticket) {
        // 1. Calculate fee
        BigDecimal fee = feeCalculator.calculateFee(ticket);
        System.out.println("Parking fee is: $" + fee);

        // 2. Process payment (dummy logic)
        System.out.println("Processing payment...");
        // paymentProcessor.process(fee);
        System.out.println("Payment successful.");

        // 3. Free the spot
        parkingLot.freeUpSpot(ticket.getParkingSpot());
        System.out.println("Spot " + ticket.getParkingSpot().getId() + " is now free. Thank you for visiting!");
    }
}

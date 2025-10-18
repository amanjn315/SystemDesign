package org.example.parkinglot;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author amanjain
 **/
public class ParkingTicket {
    private final LocalDateTime entryTime;
    private final Vehicle vehicle;
    private final ParkingSpot parkingSpot;
    private LocalDateTime exitTime;
    private BigDecimal parkingFare;


    public ParkingTicket(Vehicle vehicle, ParkingSpot parkingSpot){
        this.entryTime = LocalDateTime.now();
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public void setParkingFare(BigDecimal fare) {
        this.parkingFare = fare;
    }

    // Add getters for calculation
    public LocalDateTime getEntryTime() { return entryTime; }
    public Vehicle getVehicle() { return vehicle; }

    public BigDecimal getParkingFare(){
        return this.parkingFare;
    }

    public LocalDateTime getExitTime() {
        return this.exitTime;
    }

    public ParkingSpot getParkingSpot(){
        return this.parkingSpot;
    }
}

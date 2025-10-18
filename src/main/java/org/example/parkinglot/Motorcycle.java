package org.example.parkinglot;

/**
 * @author amanjain
 **/
public class Motorcycle extends Vehicle {
    public Motorcycle(int vehicleId, ParkingTicket parkingTicket) {
        super(vehicleId, VehicleType.MOTORCYCLE, parkingTicket);
    }
}

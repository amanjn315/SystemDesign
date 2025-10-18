package org.example.parkinglot;

/**
 * @author amanjain
 **/
public class Truck extends Vehicle {
    public Truck(int vehicleId, ParkingTicket parkingTicket) {
        super(vehicleId, VehicleType.TRUCK, parkingTicket);
    }
}

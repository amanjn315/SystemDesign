package org.example.parkinglot;

/**
 * @author amanjain
 **/
public class Car extends Vehicle {
    public Car(int vehicleId, ParkingTicket parkingTicket) {
        super(vehicleId, VehicleType.CAR, parkingTicket);
    }
}

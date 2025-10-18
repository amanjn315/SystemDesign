package org.example.parkinglot;

/**
@author amanjain
**/public abstract class Vehicle {
    private final int vehicleId;
    private final VehicleType vehicleType;

    public Vehicle(int vehicleId, VehicleType vehicleType, ParkingTicket parkingTicket){
        this.vehicleId = vehicleId;
        this.vehicleType = vehicleType;
    }

    public VehicleType getVehicleType(){
        return this.vehicleType;
    }

    public int getVehicleId(){
        return this.vehicleId;
    }
}

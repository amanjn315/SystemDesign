package org.example.parkinglot;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author amanjain
 **/
public class ParkingSpot {
    private final int id;
    private final int floorId;
    Vehicle vehicle;
    VehicleType parkingSpotType;

    public ParkingSpot(int id, int floorId, VehicleType parkingSpotType) {
        this.id = id;
        this.floorId = floorId;
        this.parkingSpotType = parkingSpotType;
    }

    public boolean isAvailable(){
        return vehicle == null;
    }

    public void parkVehicle(Vehicle vehicle){
        this.vehicle = vehicle;
    }

    public Vehicle removeVehicle(){
        Vehicle departingVehicle = this.vehicle;
        this.vehicle = null; // The spot's only responsibility is to free itself.
        return departingVehicle;
    }

    public VehicleType getParkingSpotType(){
        return this.parkingSpotType;
    }

    public int getFloorId(){
        return this.floorId;
    }

    public int getId(){
        return this.id;
    }

    public void parkingSpotSummary(){
        if(vehicle != null){
            System.out.println("Parking spot id [" + this.id + "] is of vehicle type [" + this.parkingSpotType + "] is occupied by vehicle [" + vehicle.getVehicleId() + "]");
        } else {
            System.out.println("Parking spot id [" + this.id + "] is of vehicle type [" + this.parkingSpotType + "] is current unoccupied");
        }
    }
}

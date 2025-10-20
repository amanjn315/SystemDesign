package org.example.parkinglot;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author amanjain
 **/
public class ParkingFloor {
    private final int floorId;
    private Map<Integer, ParkingSpot> parkingSpotMap;

    private Queue<ParkingSpot> availableSmallSpots;
    private Queue<ParkingSpot> availableMediumSpots;
    private Queue<ParkingSpot> availableLargeSpots;

    public ParkingFloor(int floorId) {
        this.floorId = floorId;
        parkingSpotMap = new HashMap<>();
        availableSmallSpots = new LinkedList<>();
        availableMediumSpots = new LinkedList<>();
        availableLargeSpots = new LinkedList<>();
    }

    public void parkingFloorSummary(){
        System.out.println("Parking Floor id: [" + this.floorId + "]:");
        for(ParkingSpot parkingSpot : parkingSpotMap.values()){
            parkingSpot.parkingSpotSummary();
        }
    }

    public int getFloorId(){
        return this.floorId;
    }

    public boolean addParkingSpot(ParkingSpot parkingSpot){
        if(parkingSpotMap.containsKey(parkingSpot.getId())){
            System.out.println("Parking spot with id [" + parkingSpot.getId() + "] already exist in the parking lot");
            return false;
        }
        parkingSpotMap.put(parkingSpot.getId(), parkingSpot);
        if(parkingSpot.getParkingSpotType() == VehicleType.MOTORCYCLE){
            availableSmallSpots.add(parkingSpot);
        } else if(parkingSpot.getParkingSpotType() == VehicleType.CAR){
            availableMediumSpots.add(parkingSpot);
        } else {
            availableLargeSpots.add(parkingSpot);
        }
        return true;
    }

    public ParkingSpot findAndOccupySpot(VehicleType vehicleType) {
        // Logic becomes much faster, O(1) for finding a spot
        switch (vehicleType) {
            case MOTORCYCLE:
                if(!availableSmallSpots.isEmpty()){
                    return availableSmallSpots.poll();
                }
                break;
            case CAR:
                if(!availableMediumSpots.isEmpty()){
                    return availableMediumSpots.poll();
                }
                break;
            case TRUCK:
                if(!availableLargeSpots.isEmpty()){
                    return availableLargeSpots.poll();
                }
                break;
        }
        return null; // if no spot found
    }

    public void freeSpot(ParkingSpot spot) {
        spot.removeVehicle();
        switch (spot.getParkingSpotType()) {
            case MOTORCYCLE:
                availableSmallSpots.add(spot);
                break;
            case CAR:
                availableMediumSpots.add(spot);
                break;
            case TRUCK:
                availableLargeSpots.add(spot);
        }
    }
}

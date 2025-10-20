package org.example.parkinglot;

import java.util.HashMap;
import java.util.Map;

/**
@author amanjain
**/public class ParkingLot {
    Map<Integer, ParkingFloor> parkingFloorMap;

    public ParkingLot(){
        parkingFloorMap = new HashMap<>();
    }

    public void parkingLotSummary(){
        for(ParkingFloor parkingFloor : parkingFloorMap.values()){
            parkingFloor.parkingFloorSummary();
        }
    }

    public boolean addParkingFloor(ParkingFloor parkingFloor){
        if(parkingFloorMap.containsKey(parkingFloor.getFloorId())){
            System.out.println("Parking floor with id [" + parkingFloor.getFloorId() + "] already exist in the parking lot");
            return false;
        }
        parkingFloorMap.put(parkingFloor.getFloorId(), parkingFloor);
        return true;
    }

    public ParkingTicket parkVehicle(Vehicle vehicle) {
        ParkingSpot spot = findBestAvailableSpot(vehicle.getVehicleType());

        if (spot == null) {
            // No spot available in the entire lot
            return null;
        }

        spot.parkVehicle(vehicle);
        ParkingTicket ticket = new ParkingTicket(vehicle, spot);
        return ticket;
    }

    private ParkingSpot findBestAvailableSpot(VehicleType vehicleType) {
        ParkingSpot spot = null;

        switch (vehicleType) {
            case MOTORCYCLE:
                // For a motorcycle, first try SMALL, then MEDIUM, then LARGE
                spot = findSpotAcrossFloors(VehicleType.MOTORCYCLE);
                if (spot == null) {
                    spot = findSpotAcrossFloors(VehicleType.CAR);
                }
                if (spot == null) {
                    spot = findSpotAcrossFloors(VehicleType.TRUCK);
                }
                break;
            case CAR:
                // For a car, first try MEDIUM, then LARGE
                spot = findSpotAcrossFloors(VehicleType.CAR);
                if (spot == null) {
                    spot = findSpotAcrossFloors(VehicleType.TRUCK);
                }
                break;
            case TRUCK:
                // For a truck, only try LARGE
                spot = findSpotAcrossFloors(VehicleType.TRUCK);
                break;
        }
        return spot;
    }

    private ParkingSpot findSpotAcrossFloors(VehicleType spotType) {
        for (ParkingFloor floor : parkingFloorMap.values()) {
            ParkingSpot spot = floor.findAndOccupySpot(spotType);
            if (spot != null) {
                return spot;
            }
        }
        return null;
    }

    public Vehicle freeUpSpot(ParkingSpot parkingSpot){
        return parkingSpot.removeVehicle();
    }
}

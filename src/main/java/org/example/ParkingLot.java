package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author amanjain
 **/
public class ParkingLot {
    String parkingLotId;
    List<ParkingFloor> floors;
    Map<String, Vehicle> ticketToVehicleMap;

    public ParkingLot(String parkingLotId, int numFloors, int numSlotsPerFloor) {
        this.parkingLotId = parkingLotId;
        this.floors = new ArrayList<>(numFloors);
        ticketToVehicleMap = new HashMap<>();
        for (int i = 1; i <= numFloors; i++) {
            floors.add(new ParkingFloor(i, numSlotsPerFloor));
        }
    }

    public String getParkingLotId() {
        return parkingLotId;
    }

    public List<ParkingFloor> getFloors() {
        return floors;
    }

    public Map<String, Vehicle> getTicketToVehicleMap() {
        return ticketToVehicleMap;
    }
}

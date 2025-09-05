package org.example.parkinglot;

import java.util.ArrayList;
import java.util.List;

/**
 * @author amanjain
 **/
public class ParkingFloor {
    private int floorId;
    List<ParkingSlot> slots;

    public ParkingFloor(int floorId, int numSlots) {
        this.floorId = floorId;
        this.slots = new ArrayList<>(numSlots);
        initialiseSlots(numSlots);
    }

    void initialiseSlots(int numSlots){
        slots.add(new ParkingSlot(this.floorId, 1, VehicleType.TRUCK));
        slots.add(new ParkingSlot(this.floorId, 2, VehicleType.BIKE));
        slots.add(new ParkingSlot(this.floorId, 3, VehicleType.BIKE));

        for(int i = 4; i <= numSlots; i++){
            slots.add(new ParkingSlot(this.floorId, i, VehicleType.CAR));
        }
    }

    public List<ParkingSlot> getSlots(){
        return slots;
    }

    public int getFloorId(){
        return floorId;
    }
}

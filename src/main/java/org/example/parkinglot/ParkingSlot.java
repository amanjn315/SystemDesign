package org.example.parkinglot;

/**
 * @author amanjain
 **/
public class ParkingSlot {
    private final VehicleType slotType;
    private final int slotId;
    private Vehicle parkedVehicle;

    public ParkingSlot(int floorId, int slotId, VehicleType slotType) {
        this.slotType = slotType;
        this.slotId = slotId;
        this.parkedVehicle = null;
    }

    public boolean isFree(){
        return parkedVehicle == null;
    }

    public void parkVehicle(Vehicle vehicle){
        this.parkedVehicle = vehicle;
    }

    public void unparkVehicle(){
        this.parkedVehicle = null;
    }

    public int getSlotId() {
        return slotId;
    }

    public VehicleType getSlotType() {
        return slotType;
    }
}

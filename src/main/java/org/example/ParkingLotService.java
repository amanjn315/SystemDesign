package org.example;

import java.util.stream.Collectors;

/**
 * @author amanjain
 **/
public class ParkingLotService {
    private final ParkingLot parkingLot;

    public ParkingLotService(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public void parkVehicle(VehicleType vehicleType, String regNo, String color){
        Vehicle vehicle = new Vehicle(vehicleType, regNo, color);

        for(ParkingFloor parkingFloor : parkingLot.getFloors()){
            for(ParkingSlot slot : parkingFloor.getSlots()){
                if(slot.getSlotType() == vehicleType && slot.isFree()){
                    slot.parkVehicle(vehicle);
                    String ticketId = generateTicketId(parkingFloor.getFloorId(), slot.getSlotId());
                    parkingLot.getTicketToVehicleMap().put(ticketId, vehicle);
                    System.out.println("Parked vehicle. Ticked ID: " + ticketId);
                    return;
                }
            }
        }
        System.out.println("Parking Lot Full");
    }

    public void unparkVehicle(String ticketId){
        if (!parkingLot.getTicketToVehicleMap().containsKey(ticketId)) {
            System.out.println("Invalid Ticket");
            return;
        }
        Vehicle vehicle = parkingLot.getTicketToVehicleMap().get(ticketId);
        String[] parts = ticketId.split("_");
        int floorId = Integer.parseInt(parts[1]);
        int slotId = Integer.parseInt(parts[2]);

        ParkingFloor parkingFloor = parkingLot.getFloors().get(floorId - 1);
        ParkingSlot parkingSlot = parkingFloor.getSlots().get(slotId - 1);

        parkingSlot.unparkVehicle();
        parkingLot.getTicketToVehicleMap().remove(ticketId);

        System.out.println("Unparked vehicle with Registration Number: " + vehicle.getRegistrationNumber() + " and Color: " + vehicle.getColor());
    }

    public void display(String displayType, VehicleType vehicleType) {
        for (ParkingFloor floor : parkingLot.getFloors()) {
            switch (displayType) {
                case "free_count":
                    displayFreeCount(floor, vehicleType);
                    break;
                case "free_slots":
                    displayFreeSlots(floor, vehicleType);
                    break;
                case "occupied_slots":
                    displayOccupiedSlots(floor, vehicleType);
                    break;
            }
        }
    }

    private void displayFreeCount(ParkingFloor floor, VehicleType vehicleType) {
        long count = floor.getSlots().stream()
                .filter(spot -> spot.getSlotType() == vehicleType && spot.isFree())
                .count();
        System.out.println("No. of free slots for " + vehicleType + " on Floor " + floor.getFloorId() + ": " + count);
    }

    private void displayFreeSlots(ParkingFloor floor, VehicleType vehicleType) {
        String result = floor.getSlots().stream()
                .filter(slot -> slot.getSlotType() == vehicleType && slot.isFree())
                .map(slot -> String.valueOf(slot.getSlotId()))
                .collect(Collectors.joining(","));
        System.out.println("Free slots for " + vehicleType + " on Floor " + floor.getFloorId() + ": " + result);
    }

    private void displayOccupiedSlots(ParkingFloor floor, VehicleType vehicleType) {
        String result = floor.getSlots().stream()
                .filter(slot -> slot.getSlotType() == vehicleType && !slot.isFree())
                .map(slot -> String.valueOf(slot.getSlotId()))
                .collect(Collectors.joining(","));
        System.out.println("Occupied slots for " + vehicleType + " on Floor " + floor.getFloorId() + ": " + result);
    }

    private String generateTicketId(int floorId, int spotId) {
        return parkingLot.getParkingLotId() + "_" + floorId + "_" + spotId;
    }
}

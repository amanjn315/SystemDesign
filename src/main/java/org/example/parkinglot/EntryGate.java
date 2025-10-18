package org.example.parkinglot;

/**
 * @author amanjain
 **/
public class EntryGate extends Gate {
    public EntryGate(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public ParkingTicket issueTicket(Vehicle vehicle) {
        System.out.println("Processing entry for vehicle: " + vehicle.getVehicleId());

        ParkingTicket ticket = parkingLot.parkVehicle(vehicle);
        if(ticket == null){
            System.out.println("Sorry, the parking lot is full for " + vehicle.getVehicleType());
            return null;
        }
        System.out.println("Ticket issued successfully. Please park at Floor: "
                + ticket.getParkingSpot().getFloorId() + ", Spot: " + ticket.getParkingSpot().getId());
        return ticket;
    }
}

package org.example.parkinglot;

/**
 * @author amanjain
 **/
public abstract class Gate {
    public final ParkingLot parkingLot;

    public Gate(ParkingLot parkingLot){
        this.parkingLot = parkingLot;
    }
}

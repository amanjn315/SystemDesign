package org.example.parkinglot;

import static java.lang.Thread.sleep;

/**
@author amanjain
**/public class ParkingLotService {
    public static void main(String[] args) throws InterruptedException {
        ParkingLot parkingLot = new ParkingLot();

        ParkingFloor parkingFloor1 = new ParkingFloor(1);
        ParkingFloor parkingFloor2 = new ParkingFloor(2);
        ParkingFloor parkingFloor3 = new ParkingFloor(3);

        // Add 10 parking spot to each parking floor, with 4 motorcycle, 4 car and 2 truck
        for(int i = 0; i < 10; i++){
            if(i >= 8){
                parkingFloor1.addParkingSpot(new ParkingSpot(10 + i, 1, VehicleType.TRUCK));
                parkingFloor2.addParkingSpot(new ParkingSpot(20 + i, 1, VehicleType.TRUCK));
                parkingFloor3.addParkingSpot(new ParkingSpot(30 + i, 1, VehicleType.TRUCK));
            } else if(i >= 4){
                parkingFloor1.addParkingSpot(new ParkingSpot(10 + i, 1, VehicleType.CAR));
                parkingFloor2.addParkingSpot(new ParkingSpot(20 + i, 1, VehicleType.CAR));
                parkingFloor3.addParkingSpot(new ParkingSpot(30 + i, 1, VehicleType.CAR));
            } else {
                parkingFloor1.addParkingSpot(new ParkingSpot(10 + i, 1, VehicleType.MOTORCYCLE));
                parkingFloor2.addParkingSpot(new ParkingSpot(20 + i, 1, VehicleType.MOTORCYCLE));
                parkingFloor3.addParkingSpot(new ParkingSpot(30 + i, 1, VehicleType.MOTORCYCLE));
            }
        }

        parkingLot.addParkingFloor(parkingFloor1);
        parkingLot.addParkingFloor(parkingFloor2);
        parkingLot.addParkingFloor(parkingFloor3);

        EntryGate entryGate = new EntryGate(parkingLot);
        ExitGate exitGate = new ExitGate(parkingLot, new FeeCalculator());

        Motorcycle motorcycle1 = new Motorcycle(100, null);
        Motorcycle motorcycle2 = new Motorcycle(101, null);

        Car car1 = new Car(200, null);
        Car car2 = new Car(201, null);

        Truck truck1 = new Truck(300, null);
        Truck truck2 = new Truck(301, null);

        ParkingTicket parkingTicket1 = entryGate.issueTicket(motorcycle1);
        ParkingTicket parkingTicket2 = entryGate.issueTicket(motorcycle2);

        Thread.sleep(1000);

        exitGate.processExit(parkingTicket1);
        exitGate.processExit(parkingTicket2);

//        parkingLot.parkingLotSummary();
    }
}

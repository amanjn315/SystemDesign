package org.example.parkinglot;

import java.util.Scanner;

/**
 * @author amanjain
 **///TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ParkingLot parkingLot = null;
        ParkingLotService parkingLotService = null;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String commandLine = scanner.nextLine();
            String[] commands = commandLine.split(" ");
            String command = commands[0];

            if ("exit".equals(command)) {
                break;
            }

            switch (command) {
                case "create_parking_lot":
                    parkingLot = new ParkingLot(commands[1], Integer.parseInt(commands[2]), Integer.parseInt(commands[3]));
                    parkingLotService = new ParkingLotService(parkingLot);
                    break;
                case "park_vehicle":
                    parkingLotService.parkVehicle(VehicleType.valueOf(commands[1]), commands[2], commands[3]);
                    break;
                case "unpark_vehicle":
                    parkingLotService.unparkVehicle(commands[1]);
                    break;
                case "display":
                    parkingLotService.display(commands[1], VehicleType.valueOf(commands[2]));
                    break;
                default:
                    System.out.println("Invalid command");
            }
        }
        scanner.close();
    }
}
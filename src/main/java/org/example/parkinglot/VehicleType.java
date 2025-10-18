package org.example.parkinglot;

/**
@author amanjain
**/public enum VehicleType {
    MOTORCYCLE(1),
    CAR(2),
    TRUCK(3);

    int size;

    private VehicleType(int size){
        this.size = size;
    }

    public int getSize(){
        return this.size;
    }
}

package ru.job4j.ood.srp.lsp.carparking;

import java.util.ArrayList;
import java.util.List;

public class Vehicle {

    public int size;
    public Type type;
    List<ParkingSlot> busySlot = new ArrayList<>();

        Vehicle(int size, Type type) {
        this.size = size;
        this.type = type;
        if (size > 1) {
            this.type = Type.TRUCK;
        }
    }

    public int getSize() {
        return size;
    }

    public Type getType() {
        return type;
    }
}

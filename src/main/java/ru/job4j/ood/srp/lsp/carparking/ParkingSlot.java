package ru.job4j.ood.srp.lsp.carparking;

public class ParkingSlot {
    public int size;
    public Vehicle vehicle;

    ParkingSlot(int size, Vehicle vehicle) {
        this.size = size;
        this.vehicle = vehicle;
    }

    boolean isFree() {
        if (vehicle == null) {
            return true;
        }
        return false;
    }
}

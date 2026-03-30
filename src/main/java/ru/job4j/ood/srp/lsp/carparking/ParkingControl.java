package ru.job4j.ood.srp.lsp.carparking;

import java.util.ArrayList;
import java.util.List;

public class ParkingControl {
    public List<Parking> parkingList = new ArrayList<>();

    public void addParking(Parking parking) {
        parkingList.add(parking);
    }

    public boolean park(Vehicle vehicle) {
        for (Parking parking : parkingList) {
            if (parking.park(vehicle)) {
                return true;
            }
        }
        return false;
    }

    public boolean leave(Vehicle vehicle) {
        for (Parking parking : parkingList) {
            if (parking.leave(vehicle)) {
                return true;
            }
        }
        return false;
    }
}

package ru.job4j.ood.srp.lsp.carparking;

import java.util.ArrayList;
import java.util.List;

public class Parking {

    private List<ParkingSlot> parkingSlotList = new ArrayList<>();
    private List<Vehicle> vehicles = new ArrayList<>();

    public List<ParkingSlot> getSlots() {
        return this.parkingSlotList;
    }

    public void setSlots(List<ParkingSlot> parkingSlotList) {
        this.parkingSlotList = parkingSlotList;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public boolean park(Vehicle vehicle) {
        for (ParkingSlot slot : getSlots()) {
            if ((slot.vehicle == null) && (slot.size == vehicle.size)) {
                slot.vehicle = vehicle;
                vehicles.add(vehicle);
                vehicle.busySlot.add(slot);
                return true;
            }

        }
        for (ParkingSlot slot : getSlots()) {
            if (slot.isFree() && (vehicle.getType() != Type.CAR) && (slot.size > vehicle.size)) {
                slot.vehicle = vehicle;
                vehicles.add(vehicle);
                vehicle.busySlot.add(slot);
                return true;
            }
        }
        if (parkOnAdjacentCarSlots(vehicle)) {
            return true;
        }
        return false;
    }

    public boolean leave(Vehicle vehicle) {
        if (vehicle.busySlot.isEmpty()) {
            return false;
        }
        for (ParkingSlot slot : vehicle.busySlot) {
            slot.vehicle = null;
        }
        vehicle.busySlot.clear();
        vehicles.remove(vehicle);
        return true;
    }

    private boolean parkOnAdjacentCarSlots(Vehicle vehicle) {
        int slotsNeeded = vehicle.getSize();
        for (int i = 0; i <= getSlots().size() - slotsNeeded; i++) {

            boolean allFreeAndCarType = true;

            for (int j = 0; j < slotsNeeded; j++) {
                ParkingSlot slot = getSlots().get(i + j);
                if (!slot.isFree() || slot.size != 1) {
                    allFreeAndCarType = false;
                    break;
                }
            }

            if (allFreeAndCarType) {
                for (int j = 0; j < slotsNeeded; j++) {
                    ParkingSlot slot = getSlots().get(i + j);
                    slot.vehicle = vehicle;
                    vehicle.busySlot.add(slot);
                }
                vehicles.add(vehicle);
                return true;
            }
        }
        return false;
    }
}

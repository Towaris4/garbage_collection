package ru.job4j.ood.srp.lsp.carparking;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParkingSlotTest {

    @Test
    void createParkingSlot() {
        Vehicle vehicle = new Vehicle(5, Type.TRUCK);
        ParkingSlot result = new ParkingSlot(5, vehicle);
        assertEquals(result.size, 5);
        assertEquals(result.vehicle, vehicle);
    }

    @Test
    void isFree() {
        Vehicle vehicle = new Vehicle(5, Type.TRUCK);
        ParkingSlot result = new ParkingSlot(5, vehicle);
        assertEquals(result.isFree(), false);
        assertEquals(result.vehicle, vehicle);
    }
}
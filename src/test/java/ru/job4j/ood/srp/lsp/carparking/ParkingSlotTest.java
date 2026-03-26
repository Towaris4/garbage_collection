package ru.job4j.ood.srp.lsp.carparking;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParkingSlotTest {
    @Test
    void createParkingSlot() {
        Vehicle vehicle = new Vehicle(5, Type.TRUCK);
        ParkingSlot result = new ParkingSlot(5, null);
        result.add(vehicle);
        ParkingSlot expected = new ParkingSlot(5, new Vehicle(5, Type.TRUCK));
        assertEquals(result, expected);
    }
}
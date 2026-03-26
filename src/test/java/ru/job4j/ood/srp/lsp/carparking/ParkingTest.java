package ru.job4j.ood.srp.lsp.carparking;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParkingTest {
    @Test
    void whenParkingTruckTest() {
        Parking result = new Parking(List.of(new ParkingSlot(5, null),
                new ParkingSlot(2, null),
                new ParkingSlot(3, null)));
        Vehicle truck = new Vehicle(3, Type.TRUCK);
        Parking.accept(truck);
        Parking expected = new Parking(List.of(new ParkingSlot(5, null),
                new ParkingSlot(2, null),
                new ParkingSlot(3, truck)));
        assertEquals(result, expected);
    }
}
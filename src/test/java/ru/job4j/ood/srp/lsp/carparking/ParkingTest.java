package ru.job4j.ood.srp.lsp.carparking;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParkingTest {
    @Test
    void shouldParkTruckOnTruckSlotSuccessfully() {
        Parking result = new Parking();
        result.getSlots().add(new ParkingSlot(5, null));
        result.getSlots().add(new ParkingSlot(2, null));
        result.getSlots().add(new ParkingSlot(3, null));
        Vehicle truck = new Vehicle(3, Type.TRUCK);
        result.park(truck);
        assertEquals(result.getSlots().get(2).vehicle, truck );
    }

    @Test
    void shouldParkTruckOnCarSlotsSuccessfully() {
        Parking result = new Parking();
        result.getSlots().add(new ParkingSlot(2, null));
        result.getSlots().add(new ParkingSlot(2, null));
        result.getSlots().add(new ParkingSlot(1, null));
        result.getSlots().add(new ParkingSlot(1, null));
        result.getSlots().add(new ParkingSlot(1, null));
        Vehicle truck = new Vehicle(3, Type.TRUCK);
        result.park(truck);
        assertEquals(result.getSlots().get(2).vehicle, truck);
        assertEquals(result.getSlots().get(3).vehicle, truck);
        assertEquals(result.getSlots().get(4).vehicle, truck);
    }

    @Test
    void shouldParkTruckOnLargerTruckSpotSuccessfully() {
        Parking result = new Parking();
        result.getSlots().add(new ParkingSlot(4, null));
        result.getSlots().add(new ParkingSlot(2, null));
        result.getSlots().add(new ParkingSlot(1, null));
        result.getSlots().add(new ParkingSlot(1, null));
        result.getSlots().add(new ParkingSlot(1, null));
        Vehicle truck = new Vehicle(3, Type.TRUCK);
        result.park(truck);
        assertEquals(result.getSlots().get(0).vehicle, truck);
    }

    @Test
    void shouldParkCarOnCarSlotSuccessfully() {
        Parking result = new Parking();
        result.getSlots().add(new ParkingSlot(5, null));
        result.getSlots().add(new ParkingSlot(2, null));
        result.getSlots().add(new ParkingSlot(3, null));
        result.getSlots().add(new ParkingSlot(1, null));
        Vehicle car = new Vehicle(1, Type.CAR);
        result.park(car);
        assertEquals(result.getSlots().get(3).vehicle, car);
    }

    @Test
    void shouldFailToParkCarWhenAllSpotsAreOccupied() {
        Parking result = new Parking();
        result.getSlots().add(new ParkingSlot(5, null));
        result.getSlots().add(new ParkingSlot(2, null));
        result.getSlots().add(new ParkingSlot(3, null));
        Vehicle car = new Vehicle(1, Type.CAR);
        result.park(car);
        assertEquals(result.getSlots().get(0).vehicle, null);
        assertEquals(result.getSlots().get(1).vehicle, null);
        assertEquals(result.getSlots().get(2).vehicle, null);
    }

    @Test
    void shouldFailToParkTruckWhenAllSpotsAreOccupied() {
        Parking result = new Parking();
        result.getSlots().add(new ParkingSlot(5, null));
        result.getSlots().add(new ParkingSlot(2, null));
        result.getSlots().add(new ParkingSlot(3, null));
        Vehicle truck = new Vehicle(6, Type.TRUCK);
        result.park(truck);
        assertEquals(result.getSlots().get(0).vehicle, null);
        assertEquals(result.getSlots().get(1).vehicle, null);
        assertEquals(result.getSlots().get(2).vehicle, null);
    }

    @Test
    void vehicleLeaveParking() {
        Parking result = new Parking();
        Vehicle truck = new Vehicle(5, Type.TRUCK);
        result.getSlots().add(new ParkingSlot(5, null));
        result.getSlots().add(new ParkingSlot(2, null));
        result.getSlots().add(new ParkingSlot(3, null));
        result.park(truck);
        result.leave(truck);
        assertEquals(result.getSlots().get(0).vehicle, null);
        assertEquals(result.getSlots().get(1).vehicle, null);
        assertEquals(result.getSlots().get(2).vehicle, null);
    }
}
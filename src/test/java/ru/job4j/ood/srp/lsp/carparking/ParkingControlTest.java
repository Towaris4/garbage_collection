package ru.job4j.ood.srp.lsp.carparking;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParkingControlTest {

    @Test
    void whenParkSuccessful() {
        ParkingControl parkingControl = new ParkingControl();
        Parking parking1 = new Parking();
        Parking parking2 = new Parking();
        parking1.getSlots().add(new ParkingSlot(5, null));
        parking1.getSlots().add(new ParkingSlot(2, null));
        parking1.getSlots().add(new ParkingSlot(3, null));
        parking2.getSlots().add(new ParkingSlot(5, null));
        parking2.getSlots().add(new ParkingSlot(2, null));
        parking2.getSlots().add(new ParkingSlot(6, null));
        parkingControl.addParking(parking1);
        parkingControl.addParking(parking2);
        Vehicle truck = new Vehicle(6, Type.TRUCK);
        parkingControl.park(truck);
        assertEquals(parking2.getSlots().get(2).vehicle, truck);
    }

    @Test
    void whenLeaveParking() {
        ParkingControl parkingControl = new ParkingControl();
        Parking parking1 = new Parking();
        Parking parking2 = new Parking();
        parking1.getSlots().add(new ParkingSlot(5, null));
        parking1.getSlots().add(new ParkingSlot(2, null));
        parking1.getSlots().add(new ParkingSlot(3, null));
        parking2.getSlots().add(new ParkingSlot(5, null));
        parking2.getSlots().add(new ParkingSlot(2, null));
        parking2.getSlots().add(new ParkingSlot(6, null));
        parkingControl.addParking(parking1);
        parkingControl.addParking(parking2);
        Vehicle truck = new Vehicle(6, Type.TRUCK);
        parkingControl.park(truck);
        parkingControl.leave(truck);
        assertEquals(parking2.getSlots().get(2).vehicle, null);
    }
}
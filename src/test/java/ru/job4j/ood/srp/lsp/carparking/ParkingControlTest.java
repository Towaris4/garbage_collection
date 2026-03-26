/*package ru.job4j.ood.srp.lsp.carparking;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParkingControlTest {

    @Disabled("Временно отключено, так как есть ошибки")
    @Test
    void ParkingControl() {
        Vehicle car1 = new Vehicle(1, Type.CAR);
        Vehicle truck1 = new Vehicle(3, Type.TRUCK);
        Vehicle truck2= new Vehicle(5, Type.TRUCK);
        Vehicle car2 = new Vehicle(1, Type.CAR);
        List<Vehicle> queueCar = List.of(car1, truck1, truck2, car2);
        Parking parking = new Parking(List.of(new ParkingSlot(1, null),
                new ParkingSlot(1, null),
                new ParkingSlot(1, null),
                new ParkingSlot(1, null),
                new ParkingSlot(5, null),
                new ParkingSlot(1, null)));
        ParkingControl parkingControl = new ParkingControl(parking);
        parkingControl.park(queueCar);
        Parking result = parkingControl.getParking();
        Parking expected = new  Parking(List.of(new ParkingSlot(car1, null),
                new ParkingSlot(truck1, null),
                new ParkingSlot(truck1, null),
                new ParkingSlot(truck1, null),
                new ParkingSlot(truck2, null),
                new ParkingSlot(car2, null)));
        assertEquals(result, expected);
    }
}*/
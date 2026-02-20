package ru.job4j.ood.srp.lsp.storagefood;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WarehouseTest {
    private Warehouse warehouse;
    private Food longLifeMilk;
    private Calendar now = Calendar.getInstance();

    @BeforeEach
    void setUp() {
        warehouse = new Warehouse();
        Calendar now = Calendar.getInstance();
        Calendar createDate = (Calendar) now.clone();
        createDate.add(Calendar.DAY_OF_MONTH, -5);
        Calendar expiryDate = (Calendar) now.clone();
        expiryDate.add(Calendar.DAY_OF_MONTH, 360);
        longLifeMilk = new Food("Long-life Milk", expiryDate, createDate, 120.0, 0.0);
    }

    @Test
    void acceptAddsFoodToProducts() {
        warehouse.accept(longLifeMilk, now);
        assertEquals(1, warehouse.getProductsList().size());
        assertTrue(warehouse.getProductsList().contains(longLifeMilk));
    }

    @Test
    void moveTransfersFoodToAnotherStore() {
        Shop shop = new Shop();
        warehouse.accept(longLifeMilk, now);
        warehouse.move(longLifeMilk, shop);
        assertTrue(warehouse.getProductsList().isEmpty());
        assertEquals(1, shop.getProductsList().size());
    }

}
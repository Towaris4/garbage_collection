package ru.job4j.ood.srp.lsp.storagefood;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WarehouseTest {
    private Warehouse warehouse;
    private Food longLifeMilk;

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
        warehouse.accept(longLifeMilk);
        assertEquals(1, warehouse.getProducts().size());
        assertTrue(warehouse.getProducts().contains(longLifeMilk));
    }

    @Test
    void moveTransfersFoodToAnotherStore() {
        Shop shop = new Shop();
        warehouse.accept(longLifeMilk);
        warehouse.move(longLifeMilk, shop);

        assertTrue(warehouse.getProducts().isEmpty());
        assertEquals(1, shop.getProducts().size());
    }

    @Test
    void getTypeReturnsWarehouse() {
        assertEquals("Warehouse", warehouse.getType());
    }
}
package ru.job4j.ood.srp.lsp.storagefood;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShopTest {
    private Shop shop;
    private Food milk;
    private Calendar now = Calendar.getInstance();

    @BeforeEach
    void setUp() {
        shop = new Shop();
        Calendar now = Calendar.getInstance();
        Calendar createDate = (Calendar) now.clone();
        createDate.add(Calendar.DAY_OF_MONTH, -10);
        Calendar expiryDate = (Calendar) now.clone();
        expiryDate.add(Calendar.DAY_OF_MONTH, 90);
        milk = new Food("Milk", expiryDate, createDate, 100.0, 0.0);
    }

    @Test
    void acceptAddsFoodToProducts() {
        shop.getProductsList().add(milk);
        assertEquals(1, shop.getProductsList().size());
        assertTrue(shop.getProductsList().contains(milk));
    }

    @Test
    void sellIncreasesRevenueAndRemovesFood() {
        shop.getProductsList().add(milk);
        Food bread = new Food("Bread", Calendar.getInstance(), Calendar.getInstance(), 50.0, 0.2);
        shop.getProductsList().add(bread);
        shop.sell(milk);
        shop.sell(bread);
        assertEquals(100.0 + 50.0 * 0.8, shop.getRevenue(), 0.01);
        assertTrue(shop.getProductsList().isEmpty());
    }

    @Test
    void moveTransfersFoodToAnotherStore() {
        Warehouse warehouse = new Warehouse();
        shop.getProductsList().add(milk);
        shop.move(milk, warehouse);
        assertTrue(shop.getProductsList().isEmpty());
        assertEquals(1, warehouse.getProductsList().size());
    }

}
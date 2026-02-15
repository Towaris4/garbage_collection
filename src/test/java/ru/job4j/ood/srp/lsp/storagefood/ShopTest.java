package ru.job4j.ood.srp.lsp.storagefood;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShopTest {
    private Shop shop;
    private Food milk;

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
        shop.accept(milk);
        assertEquals(1, shop.getProducts().size());
        assertTrue(shop.getProducts().contains(milk));
    }

    @Test
    void sellIncreasesRevenueAndRemovesFood() {
        shop.accept(milk);
        Food bread = new Food("Bread", Calendar.getInstance(), Calendar.getInstance(), 50.0, 0.2);
        shop.accept(bread);

        shop.sell(milk);
        shop.sell(bread);

        assertEquals(100.0 + 50.0 * 0.8, shop.getRevenue(), 0.01);
        assertTrue(shop.getProducts().isEmpty());
    }

    @Test
    void moveTransfersFoodToAnotherStore() {
        Warehouse warehouse = new Warehouse();
        shop.accept(milk);
        shop.move(milk, warehouse);

        assertTrue(shop.getProducts().isEmpty());
        assertEquals(1, warehouse.getProducts().size());
    }

    @Test
    void getTypeReturnsShop() {
        assertEquals("Shop", shop.getType());
    }
}
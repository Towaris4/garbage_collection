package ru.job4j.ood.srp.lsp.storagefood;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControlQualityTest {

    private ControlQuality control;
    private Calendar now = Calendar.getInstance();
    Shop shop = new Shop();
    Trash trash = new Trash();
    Warehouse warehouse = new Warehouse();

    @BeforeEach
    void setUp() {
        now = Calendar.getInstance();
        List<Store> store = List.of(shop, trash, warehouse);
        control = new ControlQuality(store);
    }

    private Food createFood(String name, double price, int percentUsed) {
        Calendar createDate = (Calendar) now.clone();
        createDate.add(Calendar.DAY_OF_MONTH, -percentUsed);
        Calendar expiryDate = (Calendar) now.clone();
        expiryDate.add(Calendar.DAY_OF_MONTH, 100 - percentUsed);
        return new Food(name, expiryDate, createDate, price, 0.0);
    }

    @Test
    void expiredFoodGoesToTrash() {
        Calendar createDate = (Calendar) now.clone();
        createDate.add(Calendar.DAY_OF_MONTH, -110);
        Calendar expiryDate = (Calendar) now.clone();
        expiryDate.add(Calendar.DAY_OF_MONTH, -10);
        Food expired = new Food("Expired", expiryDate, createDate, 100.0, 0.0);
        control.sort(expired);
        assertTrue(trash.getProductsList().contains(expired));
        assertFalse(shop.getProductsList().contains(expired));
        assertFalse(warehouse.getProductsList().contains(expired));
    }

    @Test
    void foodWithLessThan25PercentUsedGoesToWarehouse() {
        Food veryFresh = createFood("Very Fresh", 100.0, 10);
        control.sort(veryFresh);
        assertTrue(warehouse.getProductsList().contains(veryFresh));
        assertFalse(shop.getProductsList().contains(veryFresh));
        assertEquals(0.0, veryFresh.getDiscount(), 0.01);
    }

    @Test
    void foodWith25To75PercentUsedGoesToShopWithoutDiscount() {
        Food medium = createFood("Medium", 100.0, 50);
        control.sort(medium);
        assertTrue(shop.getProductsList().contains(medium));
        assertFalse(warehouse.getProductsList().contains(medium));
        assertEquals(0.0, medium.getDiscount(), 0.01);
    }

    @Test
    void foodWithMoreThan75PercentUsedGoesToShopWithDiscount() {
        Food old = createFood("Old", 100.0, 80);
        control.sort(old);
        assertTrue(shop.getProductsList().contains(old));
        assertEquals(20, old.getDiscount(), 0.01);
    }

    @Test
    void getExpiryPercentCalculatesCorrectly() {
        Food food = createFood("Test", 50.0, 30);
        long percent = ExpiryPercent.getExpiryPercent(food, now);
        assertEquals(30, percent);
    }

    @Test
    void multipleFoodsRedistributedCorrectly() {
        Calendar createDate1 = (Calendar) now.clone();
        createDate1.add(Calendar.DAY_OF_MONTH, -110);
        Calendar expiryDate1 = (Calendar) now.clone();
        expiryDate1.add(Calendar.DAY_OF_MONTH, -10);
        Food expired = new Food("Expired", expiryDate1, createDate1, 100.0, 0.0);
        Food veryFresh = createFood("Very Fresh", 80.0, 10);
        Food medium = createFood("Medium", 90.0, 50);
        Food old = createFood("Old", 70.0, 85);
        control.sort(expired);
        control.sort(veryFresh);
        control.sort(medium);
        control.sort(old);
        assertEquals(1, trash.getProductsList().size());
        assertTrue(trash.getProductsList().contains(expired));
        assertEquals(1, warehouse.getProductsList().size());
        assertTrue(warehouse.getProductsList().contains(veryFresh));
        assertEquals(2, shop.getProductsList().size());
        assertTrue(shop.getProductsList().contains(medium));
        assertTrue(shop.getProductsList().contains(old));
        assertEquals(0.0, medium.getDiscount(), 0.01);
        assertEquals(20, old.getDiscount(), 0.01);
    }

    @Test
    void resortIsEmptyStores() {
        assertDoesNotThrow(() -> control.resort());
        assertTrue(trash.getProductsList().isEmpty());
        assertTrue(shop.getProductsList().isEmpty());
        assertTrue(warehouse.getProductsList().isEmpty());
    }
}
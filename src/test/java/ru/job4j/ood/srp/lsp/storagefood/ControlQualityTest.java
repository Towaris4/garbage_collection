package ru.job4j.ood.srp.lsp.storagefood;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ControlQualityTest {
    private Trash trash;
    private Shop shop;
    private Warehouse warehouse;
    private ControlQuality control;
    private Calendar now;

    @BeforeEach
    void setUp() {
        now = Calendar.getInstance();
        trash = new Trash();
        shop = new Shop();
        warehouse = new Warehouse();
        control = new ControlQuality(trash, shop, warehouse);
    }

    // Вспомогательный метод для создания продуктов с заданным процентом использованного срока
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
        warehouse.accept(expired);

        control.checkQuality();

        assertTrue(trash.getProducts().contains(expired));
        assertFalse(shop.getProducts().contains(expired));
        assertFalse(warehouse.getProducts().contains(expired));
    }

    @Test
    void foodWithLessThan25PercentUsedGoesToWarehouse() {
        Food veryFresh = createFood("Very Fresh", 100.0, 10); // 10% срока использовано
        shop.accept(veryFresh);

        control.checkQuality();

        assertTrue(warehouse.getProducts().contains(veryFresh));
        assertFalse(shop.getProducts().contains(veryFresh));
        assertEquals(0.0, veryFresh.getDiscount(), 0.01);
    }

    @Test
    void foodWith25To75PercentUsedGoesToShopWithoutDiscount() {
        Food medium = createFood("Medium", 100.0, 50); // 50% срока использовано
        warehouse.accept(medium);

        control.checkQuality();

        assertTrue(shop.getProducts().contains(medium));
        assertFalse(warehouse.getProducts().contains(medium));
        assertEquals(0.0, medium.getDiscount(), 0.01);
    }

    @Test
    void foodWithMoreThan75PercentUsedGoesToShopWithDiscount() {
        Food old = createFood("Old", 100.0, 80); // 80% срока использовано
        warehouse.accept(old);

        control.checkQuality();

        assertTrue(shop.getProducts().contains(old));
        assertEquals(0.2, old.getDiscount(), 0.01);
    }

    @Test
    void getExpiryPercentCalculatesCorrectly() {
        Food food = createFood("Test", 50.0, 30); // 30% срока использовано
        long percent = control.getExpiryPercent(food, now);
        assertEquals(30, percent);
    }

    @Test
    void multipleFoodsRedistributedCorrectly() {
        // Просроченный продукт
        Calendar createDate1 = (Calendar) now.clone();
        createDate1.add(Calendar.DAY_OF_MONTH, -110);
        Calendar expiryDate1 = (Calendar) now.clone();
        expiryDate1.add(Calendar.DAY_OF_MONTH, -10);
        Food expired = new Food("Expired", expiryDate1, createDate1, 100.0, 0.0);

        // Очень свежий (<25%)
        Food veryFresh = createFood("Very Fresh", 80.0, 10);
        // Средней свежести (25-75%)
        Food medium = createFood("Medium", 90.0, 50);
        // Почти просроченный (>75%)
        Food old = createFood("Old", 70.0, 85);

        // Все продукты изначально в магазине
        shop.accept(expired);
        shop.accept(veryFresh);
        shop.accept(medium);
        shop.accept(old);

        control.checkQuality();

        // Проверка распределения
        assertEquals(1, trash.getProducts().size());
        assertTrue(trash.getProducts().contains(expired));

        assertEquals(1, warehouse.getProducts().size());
        assertTrue(warehouse.getProducts().contains(veryFresh));

        assertEquals(2, shop.getProducts().size());
        assertTrue(shop.getProducts().contains(medium));
        assertTrue(shop.getProducts().contains(old));
        assertEquals(0.0, medium.getDiscount(), 0.01);
        assertEquals(0.2, old.getDiscount(), 0.01);
    }

    @Test
    void checkQualityHandlesEmptyStores() {
        assertDoesNotThrow(() -> control.checkQuality());
        assertTrue(trash.getProducts().isEmpty());
        assertTrue(shop.getProducts().isEmpty());
        assertTrue(warehouse.getProducts().isEmpty());
    }
}
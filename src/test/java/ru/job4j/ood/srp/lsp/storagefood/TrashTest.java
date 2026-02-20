package ru.job4j.ood.srp.lsp.storagefood;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TrashTest {
    private Trash trash;
    private Food expiredMilk;
    private Calendar now = Calendar.getInstance();

    private Food createFood(String name, double price, int percentUsed) {
        Calendar createDate = (Calendar) now.clone();
        createDate.add(Calendar.DAY_OF_MONTH, -percentUsed);
        Calendar expiryDate = (Calendar) now.clone();
        expiryDate.add(Calendar.DAY_OF_MONTH, 100 - percentUsed);
        return new Food(name, expiryDate, createDate, price, 0.0);
    }

    @BeforeEach
    void setUp() {
        trash = new Trash();
        Calendar now = Calendar.getInstance();
        Calendar createDate = (Calendar) now.clone();
        createDate.add(Calendar.DAY_OF_MONTH, -100);
        Calendar expiryDate = (Calendar) now.clone();
        expiryDate.add(Calendar.DAY_OF_MONTH, -10);
        expiredMilk = new Food("Expired Milk", expiryDate, createDate, 80.0, 0.0);
    }

    @Test
    void acceptAddsFoodToProducts() {
        trash.accept(expiredMilk, now);
        assertEquals(1, trash.getProductsList().size());
        assertTrue(trash.getProductsList().contains(expiredMilk));
    }

    @Test
    void recycleClearsAllProducts() {
        trash.getProductsList().add(expiredMilk);
        trash.getProductsList().add(new Food("Another", Calendar.getInstance(), Calendar.getInstance(), 50.0, 0.0));
        assertEquals(2, trash.getProductsList().size());
        trash.recycle();
        assertTrue(trash.getProductsList().isEmpty());
    }

    @Test
    void moveTransfersFoodToAnotherStore() {
        Shop shop = new Shop();
        trash.accept(expiredMilk, now);
        trash.move(expiredMilk, shop);
        assertTrue(trash.getProductsList().isEmpty());
        assertEquals(1, shop.getProductsList().size());
    }

}
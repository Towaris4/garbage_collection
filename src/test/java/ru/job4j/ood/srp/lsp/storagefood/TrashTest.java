package ru.job4j.ood.srp.lsp.storagefood;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TrashTest {
    private Trash trash;
    private Food expiredMilk;

    @BeforeEach
    void setUp() {
        trash = new Trash();
        Calendar now = Calendar.getInstance();
        Calendar createDate = (Calendar) now.clone();
        createDate.add(Calendar.DAY_OF_MONTH, -100);
        Calendar expiryDate = (Calendar) now.clone();
        expiryDate.add(Calendar.DAY_OF_MONTH, -10); // Уже просрочен
        expiredMilk = new Food("Expired Milk", expiryDate, createDate, 80.0, 0.0);
    }

    @Test
    void acceptAddsFoodToProducts() {
        trash.accept(expiredMilk);
        assertEquals(1, trash.getProducts().size());
        assertTrue(trash.getProducts().contains(expiredMilk));
    }

    @Test
    void recycleClearsAllProducts() {
        trash.accept(expiredMilk);
        trash.accept(new Food("Another", Calendar.getInstance(), Calendar.getInstance(), 50.0, 0.0));
        assertEquals(2, trash.getProducts().size());

        trash.recycle();
        assertTrue(trash.getProducts().isEmpty());
    }

    @Test
    void moveTransfersFoodToAnotherStore() {
        Shop shop = new Shop();
        trash.accept(expiredMilk);
        trash.move(expiredMilk, shop);

        assertTrue(trash.getProducts().isEmpty());
        assertEquals(1, shop.getProducts().size());
    }

    @Test
    void getTypeReturnsTrash() {
        assertEquals("Trash", trash.getType());
    }
}
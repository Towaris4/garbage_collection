package ru.job4j.ood.srp.lsp.storagefood;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ControlQuality {

    private Trash trash;
    private Shop shop;
    private Warehouse warehouse;

    public ControlQuality(Trash trash, Shop shop, Warehouse warehouse) {
        this.trash = trash;
        this.shop = shop;
        this.warehouse = warehouse;
    }

    public void checkQuality() {
        Warehouse distrCentre = new Warehouse();
        distrCentre.products.addAll(shop.products);
        distrCentre.products.addAll(trash.products);
        distrCentre.products.addAll(warehouse.products);
        shop.products.clear();
        trash.products.clear();
        warehouse.products.clear();
        Calendar currentDate = Calendar.getInstance();
        for (Food food : distrCentre.products) {
            long expiryPercent = getExpiryPercent(food, currentDate);
            if (currentDate.after(food.getExpiryDate())) {
                trash.accept(food);
            } else if (expiryPercent < 25.0) {
                warehouse.accept(food);
            } else if (expiryPercent <= 75.0) {
                shop.accept(food);
            } else {
                food.setDiscount(0.2);
                shop.accept(food);
            }
        }
    }

    public long getExpiryPercent(Food food, Calendar currentDate) {
        long timeSinceCreation = currentDate.getTimeInMillis() - food.getCreateDate().getTimeInMillis();
        long totalLifetime = food.getExpiryDate().getTimeInMillis() - food.getCreateDate().getTimeInMillis();
        return (timeSinceCreation * 100) / totalLifetime;
    }
}

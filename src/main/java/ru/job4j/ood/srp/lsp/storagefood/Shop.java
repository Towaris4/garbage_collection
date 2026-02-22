package ru.job4j.ood.srp.lsp.storagefood;

import java.util.Calendar;

public class Shop extends AbstractStore {

    public double getRevenue() {
        return revenue;
    }

    double revenue;

    @Override
    public boolean accept(Food food, Calendar currentDate) {
        boolean add = false;
        double expiryPercent = ExpiryPercent.getExpiryPercent(food, currentDate);
        if ((expiryPercent > LOW_EXPIRATION_THRESHOLD) && (expiryPercent <= MEDIUM_EXPIRATION_THRESHOLD)) {
            products.add(food);
            add = true;
        }
        if ((expiryPercent > MEDIUM_EXPIRATION_THRESHOLD) && (expiryPercent <= EXPIRATION_LIMIT)) {
            food.setDiscount(DISCOUNT_PERCENT);
            products.add(food);
            add = true;
        }
        return add;
    }

    void sell(Food food) {
        double price = food.getPrice();
        revenue += price - food.getDiscount() * price;
        super.products.remove(food);
    }
}

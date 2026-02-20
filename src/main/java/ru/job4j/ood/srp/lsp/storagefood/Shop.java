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
        if ((expiryPercent > 25) && (expiryPercent <= 75)) {
            products.add(food);
            add = true;
        }
        if ((expiryPercent > 75) && (expiryPercent <= 100)) {
            food.setDiscount(20);
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

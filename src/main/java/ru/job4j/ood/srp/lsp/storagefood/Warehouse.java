package ru.job4j.ood.srp.lsp.storagefood;

import java.util.Calendar;

public class Warehouse extends AbstractStore {

    @Override
    public boolean accept(Food food, Calendar currentDate) {
        boolean add = false;
        double expiryPercent = ExpiryPercent.getExpiryPercent(food, currentDate);
        if ((expiryPercent >= 0) && (expiryPercent <= 25)) {
            products.add(food);
            add = true;
        }
        return add;
    }
}

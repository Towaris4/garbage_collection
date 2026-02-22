package ru.job4j.ood.srp.lsp.storagefood;

import java.util.Calendar;

public class Trash extends AbstractStore {

    public boolean accept(Food food, Calendar currentDate) {
        boolean add = false;
        double expiryPercent = ExpiryPercent.getExpiryPercent(food, currentDate);
        if (expiryPercent >= EXPIRATION_LIMIT) {
            products.add(food);
            add = true;
        }
        return add;
    }

    void recycle() {
        super.products.clear();
    }

}

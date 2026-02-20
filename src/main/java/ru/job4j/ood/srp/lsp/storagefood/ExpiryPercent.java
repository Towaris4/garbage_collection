package ru.job4j.ood.srp.lsp.storagefood;

import java.util.Calendar;

public class ExpiryPercent {

    public static long getExpiryPercent(Food food, Calendar currentDate) {
        long timeSinceCreation = currentDate.getTimeInMillis() - food.getCreateDate().getTimeInMillis();
        long totalLifetime = food.getExpiryDate().getTimeInMillis() - food.getCreateDate().getTimeInMillis();
        return  (timeSinceCreation * 100) / totalLifetime;
    }
}

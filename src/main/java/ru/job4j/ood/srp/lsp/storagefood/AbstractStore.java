package ru.job4j.ood.srp.lsp.storagefood;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {

    protected static final double FRESHNESS_START = 0;
    protected static final double LOW_EXPIRATION_THRESHOLD = 25;
    protected static final double MEDIUM_EXPIRATION_THRESHOLD = 75;
    protected static final double EXPIRATION_LIMIT = 100;
    protected static final double DISCOUNT_PERCENT = 20;

    public List<Food> getProductsList() {
        return products;
    }

    protected List<Food> products = new ArrayList<>();

    @Override
    public void move(Food food, Store store) {
        store.getProductsList().add(food);
        products.remove(food);
    }
}

package ru.job4j.ood.srp.lsp.storagefood;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {

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

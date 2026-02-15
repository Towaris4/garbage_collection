package ru.job4j.ood.srp.lsp.storagefood;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {
    @Override
    public List<Food> getProducts() {
        return products;
    }

    protected List<Food> products = new ArrayList<>();

    @Override
    public void accept(Food food) {
        products.add(food);
    }

    @Override
    public void move(Food food, Store store) {
        products.remove(food);
        store.accept(food);
    }

    @Override
    public abstract String getType();
}

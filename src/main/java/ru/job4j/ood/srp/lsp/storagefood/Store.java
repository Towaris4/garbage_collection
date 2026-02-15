package ru.job4j.ood.srp.lsp.storagefood;

import java.util.ArrayList;
import java.util.List;

public interface Store {

    public void accept(Food food);

    public String getType();

    public void move(Food food, Store store);

    public List<Food> getProducts();
}

package ru.job4j.ood.srp.lsp.storagefood;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public interface Store {

    public boolean accept(Food food, Calendar currentDate);

    public void move(Food food, Store store);

    public List<Food> getProductsList();
}

package ru.job4j.ood.srp.lsp.storagefood;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class ControlQuality {

    private final List<Store> stores;
    private List<Food> sortableFood = new ArrayList<>();

    private Calendar currentDate;

    public ControlQuality(List<Store> stores) {
        this.stores = new ArrayList<>(stores);
    }

    public void resort() {
        this.currentDate = Calendar.getInstance();
        for (Store store : stores) {
            sortableFood.addAll(store.getProductsList());
            store.getProductsList().clear();
        }
        for (Food food : sortableFood) {
            sort(food);
        }
    }

    public void sort(Food food) {
        this.currentDate = Calendar.getInstance();
        sortableFood.add(food);
        for (Store store : stores) {
            if (store.accept(food, currentDate)) {
                sortableFood.remove(food);
                break;
            }
        }
    }
}
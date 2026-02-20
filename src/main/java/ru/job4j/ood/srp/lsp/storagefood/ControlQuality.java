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

    public void checkQuality() {
        Calendar currentDate = Calendar.getInstance();
        for (Store store : stores) {
            sortableFood.addAll(store.getProductsList());
            store.getProductsList().clear();
        }
        Iterator<Food> it = sortableFood.iterator();
        while (it.hasNext()) {
            Food product = it.next();
            for (Store store : stores) {
                if (store.accept(product, currentDate)) {
                    it.remove();
                    break;
                }
            }
        }
    }
}
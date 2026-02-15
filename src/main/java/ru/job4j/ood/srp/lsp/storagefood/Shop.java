package ru.job4j.ood.srp.lsp.storagefood;

public class Shop extends AbstractStore {

    public double getRevenue() {
        return revenue;
    }

    double revenue;

    @Override
    public String getType() {
        return "Shop";
    }

    void sell(Food food) {
        double price = food.getPrice();
        revenue += price - food.getDiscount() * price;
        super.products.remove(food);
    }
}

package ru.job4j.ood.srp.lsp.storagefood;

public class Trash extends AbstractStore {

    void recycle() {
        super.products.clear();
    }

    @Override
    public String getType() {
        return "Trash";
    }
}

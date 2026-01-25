package ru.job4j.ood.srp.lsp;

import java.util.Arrays;
import java.util.List;

class LazyDataProvider extends DataProvider {
    private boolean loaded = false;

    @Override
    public List<String> getData() {
        if (!loaded) {
            return null;
        }
        return List.of("real data");
    }

    public void load() {
        loaded = true;
    }
}

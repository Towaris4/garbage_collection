package ru.job4j.ood.srp.lsp.ispviolation;

public class SimplePrinter implements MultiFunctionDevice {
    public void print() {
    }

    public void scan() {
        System.out.println("Ошибка: Сканирование не поддерживается");
    }

    public void fax() {
    }
}

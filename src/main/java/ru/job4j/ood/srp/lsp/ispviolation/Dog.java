package ru.job4j.ood.srp.lsp.ispviolation;

class Dog implements Animal {
    public void walk() {
    }

    public void swim() {
    }

    public void fly() {
        throw new UnsupportedOperationException("Собаки не летают!");
    }
}
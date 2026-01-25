package ru.job4j.ood.srp.lsp;

// Базовый класс
class Vehicle {
    protected double fuel;

    public void refuel(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        this.fuel += amount;
    }
}


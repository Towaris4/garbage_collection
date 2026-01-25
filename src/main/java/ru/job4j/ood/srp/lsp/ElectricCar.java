package ru.job4j.ood.srp.lsp;

class ElectricCar extends Vehicle {
    @Override
    public void refuel(double amount) {
        if (amount <= 0 || amount != Math.floor(amount)) {
            throw new IllegalArgumentException("Amount must be a positive integer (in kWh)");
        }
        this.fuel += amount;
    }
}
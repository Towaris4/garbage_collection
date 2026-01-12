package ru.job4j.ood.srp;

public class User {
    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Ответственность 1: хранение данных пользователя
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    // Ответственность 2: сохранение в базу данных — НАРУШЕНИЕ SRP
    public void saveToDatabase() {
        // код для подключения к БД и сохранения
        System.out.println("Сохраняем пользователя " + name + " в БД");
    }

    // Ответственность 3: отправка email — ещё одно нарушение SRP
    public void sendEmail(String message) {
        // код для отправки email
        System.out.println("Отправляем email " + email + ": " + message);
    }
}
package ru.job4j.ood.ocp;

    public class PaymentProcessor {
        public void process(String method, double amount) {
            if ("creditCard".equals(method)) {
                System.out.println("Обработано картой");
            } else if ("paypal".equals(method)) {
                System.out.println("Обработано paypal");
            }
        }
    }

package ru.job4j.ood.ocp;

    public class PaymentProcessor {
        public void process(String method, double amount) {
            if ("creditCard".equals(method)) {
                // обработка картой
            } else if ("paypal".equals(method)) {
                // обработка через PayPal
            }
            // Нарушение OCP: при добавлении Apple Pay — правим код
        }
    }

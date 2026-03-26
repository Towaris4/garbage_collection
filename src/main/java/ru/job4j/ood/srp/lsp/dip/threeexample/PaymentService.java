package ru.job4j.ood.srp.lsp.dip.threeexample;

class PaymentService {
    private FileLogger logger = new FileLogger();

    public void processPayment(double amount) {
        logger.log("Processing payment: " + amount);
        logger.log("Payment completed");
    }
}


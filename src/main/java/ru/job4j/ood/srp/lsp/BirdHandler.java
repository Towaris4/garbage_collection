package ru.job4j.ood.srp.lsp;

public class BirdHandler {
    public void makeBirdFly(Bird bird) {
        if (bird instanceof Penguin) {
            System.out.println("This bird is a penguin. It cannot fly.");
            return;
        }
        bird.fly();
    }
}
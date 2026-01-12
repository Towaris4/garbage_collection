package ru.job4j.ood.srp;

import java.io.FileWriter;
import java.io.IOException;

public class Report {
    private String content;

    public Report(String content) {
        this.content = content;
    }

    public String generate() {
        return "=== ОТЧЁТ ===\n" + content;
    }

    public void printToConsole() {
        System.out.println(generate());
    }

    public void saveToFile(String filename) throws IOException {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(generate());
        }
    }
}
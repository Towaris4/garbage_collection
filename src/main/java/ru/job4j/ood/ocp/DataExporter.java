package ru.job4j.ood.ocp;

public class DataExporter {
    public void export(String format, Data data) {
        if ("json".equals(format)) {
            System.out.println("Сериализовано в JSON");
        } else if ("xml".equals(format)) {
            System.out.println("Сериализовано в XML");
        }
    }

    class Data {
        String name;
        String number;
    }
}
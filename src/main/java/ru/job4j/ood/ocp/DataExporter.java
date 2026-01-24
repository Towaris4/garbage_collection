package ru.job4j.ood.ocp;

public class DataExporter {
    public void export(String format, Data data) {
        if ("json".equals(format)) {
            serializeToJson();
        } else if ("xml".equals(format)) {
            serializeToXML();
        }
    }

    public void serializeToJson() {
    }

    public void serializeToXML() {
    }

    class Data {
        String name;
        String number;
    }
}
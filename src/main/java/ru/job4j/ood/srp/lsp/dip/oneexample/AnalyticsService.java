package ru.job4j.ood.srp.lsp.dip.oneexample;

class AnalyticsService {
    private ExcelExporter exporter = new ExcelExporter();

    public void generateReport(Data data) {
        exporter.export(data);
    }
}


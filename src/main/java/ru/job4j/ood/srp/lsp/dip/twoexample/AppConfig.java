package ru.job4j.ood.srp.lsp.dip.twoexample;

class AppConfig {
    private XmlFileReader reader = new XmlFileReader();

    public String getSetting(String key) {
        return reader.read("config.xml", key);
    }
}


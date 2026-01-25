package ru.job4j.ood.srp.report;

import jakarta.xml.bind.JAXBException;
import ru.job4j.ood.srp.model.Employee;

import java.io.IOException;
import java.util.function.Predicate;

public interface Report {
    String generate(Predicate<Employee> filter) throws JAXBException, IOException;
}
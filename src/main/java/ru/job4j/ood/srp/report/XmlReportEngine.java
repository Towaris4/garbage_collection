package ru.job4j.ood.srp.report;

import jakarta.xml.bind.*;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

public class XmlReportEngine implements Report {

    private final Store store;

    public XmlReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        Employees employees = new Employees(store.findBy(filter));
        String xml = "";

        try (StringWriter writer = new StringWriter()) {
            JAXBContext context = JAXBContext.newInstance(Employee.class, XmlReportEngine.Employees.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(employees, writer);
            xml = writer.toString();
        } catch (JAXBException e) {
            throw new RuntimeException("Ошибка при маршалинге XML", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return xml;
    }

    @XmlRootElement(name = "employees")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Employees {

        @XmlElement(name = "employee")
        private List<Employee> employees;

        public Employees() {

        }

        public Employees(List<Employee> employees) {
            this.employees = employees;
        }

        public List<Employee> getEmployees() {
            return employees;
        }

        public void setEmployees(List<Employee> employees) {
            this.employees = employees;
        }
    }

    public static class CalendarAdapter extends XmlAdapter<String, Calendar> {

        private static final String PATTERN = "dd:MM:yyyy HH:mm";
        private static final SimpleDateFormat FORMATTER = new SimpleDateFormat(PATTERN);

        static {
            FORMATTER.setLenient(false);
        }

        @Override
        public Calendar unmarshal(String text) throws Exception {
            if (text == null || text.isEmpty()) {
                return null;
            }
            Date date = FORMATTER.parse(text);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar;
        }

        @Override
        public String marshal(Calendar calendar) throws Exception {
            if (calendar == null) {
                return null;
            }
            return FORMATTER.format(calendar.getTime());
        }
    }
}
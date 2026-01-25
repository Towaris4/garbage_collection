package ru.job4j.ood.srp.report;
import com.google.gson.*;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Predicate;
import com.google.gson.*;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class JsonReportEngine implements Report {

    private final Store store;

    public JsonReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = store.findBy(filter);
        final Gson gson = new GsonBuilder()
                .registerTypeAdapter(Calendar.class, new CalendarSerializer())
                .registerTypeAdapter(GregorianCalendar.class, new CalendarSerializer())
                .setPrettyPrinting()
                .create();
        return gson.toJson(employees);
    }

    public static class CalendarSerializer implements JsonSerializer<Calendar> {
        private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd:MM:yyyy HH:mm");

        static {
            FORMATTER.setTimeZone(TimeZone.getDefault()); // или TimeZone.getTimeZone("UTC")
        }

        @Override
        public JsonElement serialize(Calendar src, Type typeOfSrc, JsonSerializationContext context) {
            if (src == null) {
                return JsonNull.INSTANCE;
            }
            return new JsonPrimitive(FORMATTER.format(src.getTime()));
        }
    }
}
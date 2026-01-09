package ru.job4j.template;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.tdd.*;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class TemplateTest {
    @Test
    public void whenTemplateInvalidThenException() {
        TemplateGenerator generator = new TemplateGenerator();
        String template = null;
        Map<String, String> args = Map.of("name", "Petr Arsentev", "subject", "you");
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenArgsNotEnoughThenException() {
        TemplateGenerator generator = new TemplateGenerator();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = Map.of("name", "Petr Arsentev");
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenArgsRedundantThenException() {
        TemplateGenerator generator = new TemplateGenerator();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = Map.of("name", "Petr Arsentev", "subject", "you", "words", "Hello worlds!");
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenArgsInvalidThenException() {
        TemplateGenerator generator = new TemplateGenerator();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = null;
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
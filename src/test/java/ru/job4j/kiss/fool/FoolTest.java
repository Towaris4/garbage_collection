package ru.job4j.kiss.fool;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FoolTest {
    @Test
    void fizzBuzz() {
            assertEquals("1", Fool.fizzBuzz(1));
            assertEquals("2", Fool.fizzBuzz(2));
            assertEquals("Fizz", Fool.fizzBuzz(3));
            assertEquals("4", Fool.fizzBuzz(4));
            assertEquals("Buzz", Fool.fizzBuzz(5));
            assertEquals("Fizz", Fool.fizzBuzz(6));
            assertEquals("FizzBuzz", Fool.fizzBuzz(15));
            assertEquals("Fizz", Fool.fizzBuzz(99));
            assertEquals("Buzz", Fool.fizzBuzz(100));
        }
    }
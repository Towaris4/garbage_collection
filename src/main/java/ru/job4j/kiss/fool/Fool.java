package ru.job4j.kiss.fool;

import java.util.Scanner;

public class Fool {

    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz.");
        Scanner input = new Scanner(System.in);
        int number = 1;
        while (number <= 100) {
            System.out.println(fizzBuzz(number));
            number++;
            String expected = fizzBuzz(number);
            String answer = input.nextLine();
            if (!expected.equals(answer)) {
                System.out.println("Ошибка. Начинай снова.");
                number = 0;
            }
            number++;
        }
    }

    public static String fizzBuzz(int n) {
        if (n % 15 == 0) {
            return "FizzBuzz";
        }
        if (n % 3 == 0) {
            return "Fizz";
        }
        if (n % 5 == 0) {
            return "Buzz";
        }
        return String.valueOf(n);
    }
}

package ru.job4j.gc.ref;

import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

public class MySoftDemo {

    public static void main(String[] args) throws InterruptedException {
        SoftReference<String> softReference = new SoftReference<>("Hello world");
    }
}
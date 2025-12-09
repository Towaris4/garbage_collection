package ru.job4j.gc.ref;

import java.lang.ref.WeakReference;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyWeakDemo {

    public static void main(String[] args) throws InterruptedException {
        String object = new String("Hello world");
        WeakReference<String> weakReference = new WeakReference<String>(object);
    }
}
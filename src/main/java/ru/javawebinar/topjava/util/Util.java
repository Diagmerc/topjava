package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalTime;

public class Util {
    public static LocalTime minTime = LocalTime.MIN;
    public static LocalTime maxTime = LocalTime.MAX;
    public static LocalDate minDate = LocalDate.of(2000, 1, 1);
    public static LocalDate maxDate = LocalDate.of(2100, 12, 31);

    public static <T extends Comparable<T>> boolean isBetweenHalfOpen(T value, T start, T end) {
        return (start == null || value.compareTo(start) >= 0) && (end == null || value.compareTo(end) < 0);
    }
}

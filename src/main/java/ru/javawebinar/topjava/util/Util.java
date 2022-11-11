package ru.javawebinar.topjava.util;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalTime;

public class Util {
    public static LocalTime minTime = LocalTime.of(0, 0);
    public static LocalTime maxTime = LocalTime.of(23, 59);
    public static LocalDate minDate = LocalDate.of(2000, 1, 1);
    public static LocalDate maxDate = LocalDate.of(2100, 12, 31);

    public static <T extends Comparable<T>> boolean isBetweenHalfOpen(T value, T start, T end) {
        return (start == null || value.compareTo(start) >= 0) && (end == null || value.compareTo(end) < 0);
    }

    public static @Nullable
    LocalTime parseLocalTime(@Nullable String time) {
        return StringUtils.isEmpty(time) ? null : LocalTime.parse(time);
    }

    public static @Nullable
    LocalDate parseLocalDate(@Nullable String date) {
        return StringUtils.isEmpty(date) ? null : LocalDate.parse(date);
    }
}

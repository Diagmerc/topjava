package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MealTestData {
    public static final Meal USER_MEAL1 = new Meal(100006, LocalDateTime.of(2022, 01, 22, 21, 00), "ужин", 200);
    public static final Meal USER_MEAL2 = new Meal(100007, LocalDateTime.of(2022, 01, 22, 15, 00), "обед", 300);

    public static final Meal USER_MEAL3 = new Meal(100009, LocalDateTime.of(2020, 01, 31, 0, 0), "Еда на граничное значение", 2001);
    public static final Meal USER_MEAL4 = new Meal(100008, LocalDateTime.of(2022, 01, 25, 15, 00), "обед25", 300);
    public static final Meal ADMIN_MEAL3 = new Meal(100005, LocalDateTime.of(2022, 01, 22, 10, 00), "завтрак", 500);

    public static final LocalDate START_TIME = LocalDate.of(2022, 01, 24);

    public static final LocalDate END_TIME = LocalDate.of(2022, 01, 26);

    public static Meal getNewMeal() {
        return new Meal(null, LocalDateTime.now(), "newdescription", 999);
    }

    public static Meal getUpdatedMeal() {
        Meal updated = new Meal(USER_MEAL1);
        updated.setCalories(999);
        updated.setDescription("description");
        updated.setDateTime(LocalDateTime.now());
        return updated;
    }
}

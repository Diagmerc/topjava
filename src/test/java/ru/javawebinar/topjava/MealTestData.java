package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class MealTestData {
    public static final Meal userMeal1 = new Meal(100006, LocalDateTime.of(2022, 01, 22, 21, 00), "ужин", 200);
    public static final Meal userMeal2 = new Meal(100007, LocalDateTime.of(2022, 01, 22, 15, 00), "обед", 300);

    public static final Meal userMeal3 = new Meal(100009, LocalDateTime.of(2020, 01, 31, 0, 0), "Еда на граничное значение", 2001);
    public static final Meal userMeal4 = new Meal(100008, LocalDateTime.of(2022, 01, 25, 15, 00), "обед25", 300);
    public static final Meal adminMeal3 = new Meal(100005, LocalDateTime.of(2022, 01, 22, 10, 00), "завтрак", 500);

    public static final LocalDate START_TIME = LocalDate.of(2022, 01, 24);

    public static final LocalDate END_TIME = LocalDate.of(2022, 01, 26);

    public static Meal getNewMeal() {
        return new Meal(null, LocalDateTime.of(2000,1,1,1,1,1), "newdescription", 999);
    }

    public static Meal getUpdatedMeal() {
        Meal updated = new Meal(userMeal1);
        updated.setCalories(999);
        updated.setDescription("description");
        updated.setDateTime(LocalDateTime.of(2001,1,1,1,1,1));
        return updated;
    }
    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveFieldByFieldElementComparatorIgnoringFields().isEqualTo(expected);
    }
}

package ru.javawebinar.topjava.repo;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class RepoImpl implements Repository {
    AtomicInteger id = new AtomicInteger(1);
    Map<Integer, Meal> meals = new ConcurrentHashMap<>();
    {
                saveElement(new Meal(0,LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
                saveElement(new Meal(0,LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
                saveElement(new Meal(0,LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
                saveElement(new Meal(0,LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
                saveElement(new Meal(0,LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
                saveElement(new Meal(0,LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
                saveElement(new Meal(0,LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    public List getElements(){
        return new ArrayList<>(meals.values());
    }
    @Override
    public void saveElement(Meal meal) {
        int newId = id.getAndIncrement();
        meals.put(newId, meal);
        meal.setId(newId);
    }

    @Override
    public void deleteElement(int id) {
        meals.remove(id);
    }

    @Override
    public void updateElement(int id, Meal meal) {
        meals.replace(id, meal);
    }
}

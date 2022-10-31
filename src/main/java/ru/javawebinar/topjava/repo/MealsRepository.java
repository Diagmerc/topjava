package ru.javawebinar.topjava.repo;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

public interface MealsRepository {
    Collection<Meal> getAll();

    Meal save(Meal meal);

    void delete(int id);

    Meal getById(int id);
}

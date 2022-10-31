package ru.javawebinar.topjava.repo;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

public interface Repository {
    Collection<Meal> getElements();

    Meal save(Meal meal);

    void delete(int id);

    Meal getElement(int id);
}

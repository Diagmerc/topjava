package ru.javawebinar.topjava.repo;

import ru.javawebinar.topjava.model.Meal;
import java.util.Collection;

public interface Repository {
    Collection<Meal> getElements();
    void saveElement(Meal meal);
    void deleteElement(int id);
    void updateElement(int id, Meal meal);
}

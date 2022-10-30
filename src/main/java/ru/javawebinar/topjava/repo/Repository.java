package ru.javawebinar.topjava.repo;

import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface Repository {
    Collection<Meal> getElements();
    public void saveElement(Meal meal);
    public void deleteElement(int id);
    public void updateElement(int id, Meal meal);
    public Meal getElement(int id);
}

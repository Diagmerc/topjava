package ru.javawebinar.topjava.repo;

import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Repository {
    List getElements();
    public void saveElement(Meal meal);
    public void deleteElement(int id);
    public void updateElement(int id, Meal meal);
}

package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.util.Collection;

@Service
public class MealService {

    private MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal save(Meal meal){
        return repository.save(meal);
    }

    public boolean delete(int id){
        return repository.delete(id);
    }

    public Meal get(int id){
        return repository.get(id);
    }

    public Collection<Meal> getAll(){
        return repository.getAll();
    }
}
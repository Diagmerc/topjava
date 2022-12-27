package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaMealRepository implements MealRepository {

    private final CrudMealRepository crudRepository;

    private final CrudUserRepository crudUserRepository;

    public DataJpaMealRepository(CrudMealRepository crudRepository, CrudUserRepository crudUserRepository) {
        this.crudRepository = crudRepository;
        this.crudUserRepository = crudUserRepository;
    }

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        if (meal.isNew() || get(meal.id(), userId) != null) {
            User referenceUserById = crudUserRepository.getReferenceById(userId);
            meal.setUser(referenceUserById);
            return crudRepository.save(meal);
        } else {
            return null;
        }
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudRepository.delete(id, userId) != 0;
    }

    @Override
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Meal get(int id, int userId) {
        return crudRepository.getByIdAndUser(id, userId);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return crudRepository.getAllByUser(userId);
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return crudRepository.getBetween(startDateTime, endDateTime, userId);
    }
}

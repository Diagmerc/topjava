package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.Util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepository.class);
    private final Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(meal -> save(meal, 1));
        save(new Meal(LocalDateTime.now(), "обед" + " user2", 100500), 2);
        save(new Meal(LocalDateTime.now(), "Ужин" + " user2", 10), 2);
    }

    @Override
    public Meal save(Meal meal, int userId) {
        Map<Integer, Meal> userRepository = repository.computeIfAbsent(userId, (key) -> new ConcurrentHashMap<>());
        log.info("save {}", meal);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            userRepository.put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        return userRepository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        Map<Integer, Meal> userRepository = repository.computeIfAbsent(userId, (key) -> new ConcurrentHashMap<>());
        log.info("delete {}", id);
        return userRepository != null && userRepository.remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        Map<Integer, Meal> userRepository = repository.computeIfAbsent(userId, (key) -> new ConcurrentHashMap<>());
        log.info("get {}", id);
        return userRepository == null ? null : userRepository.get(id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        Map<Integer, Meal> userRepository = repository.computeIfAbsent(userId, (key) -> new ConcurrentHashMap<>());
        log.info("getAll");
        return userRepository.values()
                .stream()
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> getAllFilteredByDateTime(LocalDate start, LocalDate end, int userId) {
        log.info("getAllFilteredByDateTime");
        return getAll(userId)
                .stream()
                .filter(meal -> Util.isBetweenHalfOpen(meal.getDate(), start, end))
                .collect(Collectors.toList());
    }
}


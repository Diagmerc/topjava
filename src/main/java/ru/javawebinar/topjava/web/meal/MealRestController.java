package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(MealRestController.class);

    @Autowired
    private MealService service;

    public Meal save(Meal meal, int userId) {
        log.info("save {}", meal);
        return service.save(meal, userId);
    }

    public void delete(int id, int userId) {
        log.info("delete {}", id);
        service.delete(id, userId);
    }

    public Meal get(int id, int userId) {
        log.info("get {}", id);
        return service.get(id, userId);
    }

    public Collection<Meal> getAll(int userId) {
        log.info("getAll {}", userId);
        return service.getAll(userId);
    }

    public Collection<MealTo> getAllFilteredByDateTime(LocalTime startTime, LocalTime endTime, LocalDate startDate, LocalDate endDate) {
        log.info("getAllFilteredByDateTime");
        Collection<Meal> allFilteredByDateTime = service.getAllFilteredByDateTime(startDate, endDate, SecurityUtil.authUserId());
        return MealsUtil.getFilteredTos(allFilteredByDateTime, SecurityUtil.authUserCaloriesPerDay(),startTime,endTime);
    }
}
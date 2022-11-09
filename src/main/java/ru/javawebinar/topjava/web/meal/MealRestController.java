package ru.javawebinar.topjava.web.meal;

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
    @Autowired
    private MealService service;

    public Meal save(Meal meal) {
        return service.save(meal);
    }

    public void delete(int id) {
        service.delete(id);
    }

    public Meal get(int id) {
        return service.get(id);
    }

    public Collection<Meal> getAll() {
        return service.getAll(SecurityUtil.authUserId());
    }

    public Collection<MealTo> getAllFilteredByDateTime(LocalTime startTime, LocalTime endTime, LocalDate startDate, LocalDate endDate) {
        Collection<Meal> allFilteredByDateTime = service.getAllFilteredByDateTime(startDate, endDate, SecurityUtil.authUserId());
        return MealsUtil.getFilteredTos(allFilteredByDateTime, SecurityUtil.authUserCaloriesPerDay(),startTime,endTime);
    }
}
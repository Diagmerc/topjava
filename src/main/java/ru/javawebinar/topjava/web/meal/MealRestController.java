package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.Util;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(MealRestController.class);

    @Autowired
    private MealService service;

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        checkNew(meal);
        return service.create(meal, SecurityUtil.authUserId());
    }

    public void update(Meal meal, int id) {
        log.info("update {} with id={}", meal, meal.getId());
        assureIdConsistent(meal, id);
        service.update(meal, SecurityUtil.authUserId());
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id, SecurityUtil.authUserId());
    }

    public Meal get(int id) {
        log.info("get {}", id);
        return service.get(id, SecurityUtil.authUserId());
    }

    public List<MealTo> getAll() {
        log.info("getAll {}", SecurityUtil.authUserId());
        return addUserIdforMeals(MealsUtil.getTos(service.getAll(SecurityUtil.authUserId()), MealsUtil.DEFAULT_CALORIES_PER_DAY));
    }

    public List<MealTo> getAllFilteredByDateTime(LocalTime startTime, LocalTime endTime, LocalDate startDate, LocalDate endDate) {
        log.info("getAllFilteredByDateTime");
        startTime = startTime == null ? Util.minTime : startTime;
        endTime = endTime == null ? Util.maxTime : endTime;
        startDate = startDate == null ? Util.minDate : startDate;
        endDate = endDate == null ? Util.maxDate : endDate;
        Collection<Meal> allFilteredByDateTime = service.getAllFilteredByDateTime(startDate, endDate, SecurityUtil.authUserId());
        return addUserIdforMeals(MealsUtil.getFilteredTos(allFilteredByDateTime, SecurityUtil.authUserCaloriesPerDay(), startTime, endTime));
    }

    public List<MealTo> addUserIdforMeals(List<MealTo> mealToList) {
        mealToList.stream().forEach(mealTo -> mealTo.setUserId(SecurityUtil.authUserId()));
        return mealToList;
    }
}
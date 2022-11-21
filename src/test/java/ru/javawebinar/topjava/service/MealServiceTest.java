package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {
    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal meal = service.get(userMeal1.getId(), USER_ID);
        MealTestData.assertMatch(meal, userMeal1);
    }

    @Test
    public void getWithAnotherUser() {
        assertThrows(NotFoundException.class, () -> service.get(userMeal1.getId(), ADMIN_ID));
    }

    @Test
    public void delete() {
        service.delete(adminMeal3.getId(), ADMIN_ID);
        assertThrows(NotFoundException.class, () -> service.delete(adminMeal3.getId(), ADMIN_ID));
    }

    @Test
    public void deleteWithAnotherUser() {
        assertThrows(NotFoundException.class, () -> service.delete(adminMeal3.getId(), USER_ID));
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> betweenInclusive = service.getBetweenInclusive(START_TIME, END_TIME, USER_ID);
        MealTestData.assertMatch(betweenInclusive, userMeal4);
    }

    @Test
    public void getAll() {
        List<Meal> all = service.getAll(USER_ID);
        MealTestData.assertMatch(all, Arrays.asList(userMeal3, userMeal2, userMeal1, userMeal4));
    }

    @Test
    public void update() {
        Meal updated = getUpdatedMeal();
        service.update(updated, USER_ID);
        MealTestData.assertMatch(service.get(userMeal1.getId(), USER_ID), getUpdatedMeal());
    }

    @Test
    public void updateWithAnotherUser() {
        assertThrows(NotFoundException.class, () -> service.update(userMeal1, ADMIN_ID));
    }

    @Test
    public void create() {
        Meal created = service.create(getNewMeal(), USER_ID);
        Integer newId = created.getId();
        Meal newMeal = getNewMeal();
        newMeal.setId(newId);
        MealTestData.assertMatch(created, newMeal);
        MealTestData.assertMatch(service.get(newId, USER_ID), newMeal);
    }

    @Test
    public void duplicateDateTimeCreate() {
        LocalDateTime duplicateDate = service.get(userMeal1.getId(), USER_ID).getDateTime();
        assertThrows(DataAccessException.class,
                () -> service.create(new Meal(null, duplicateDate, "ужин", 1000), USER_ID));
    }
}
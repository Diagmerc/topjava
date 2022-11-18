package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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
        Meal meal = service.get(USER_MEAL1.getId(), USER_ID);
        assertThat(meal).isEqualTo(USER_MEAL1);
    }

    @Test
    public void getWithAnotherUser() {
        assertThrows(NotFoundException.class, () -> service.get(USER_MEAL1.getId(), ADMIN_ID));
    }

    @Test
    public void delete() {
        service.delete(ADMIN_MEAL3.getId(), ADMIN_ID);
        assertThrows(NotFoundException.class, () -> service.delete(ADMIN_MEAL3.getId(), ADMIN_ID));
    }

    @Test
    public void deleteWithAnotherUser() {
        assertThrows(NotFoundException.class, () -> service.delete(ADMIN_MEAL3.getId(), USER_ID));
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> betweenInclusive = service.getBetweenInclusive(START_TIME, END_TIME, USER_ID);
        assertThat(betweenInclusive).isEqualTo(Arrays.asList(USER_MEAL4));
    }

    @Test
    public void getAll() {
        List<Meal> all = service.getAll(USER_ID);
        assertThat(all).isEqualTo(Arrays.asList(USER_MEAL3, USER_MEAL2, USER_MEAL1, USER_MEAL4));
    }

    @Test
    public void update() {
        Meal updated = getUpdatedMeal();
        service.update(updated, USER_ID);
        assertThat(service.get(USER_MEAL1.getId(), USER_ID)).isEqualTo(getUpdatedMeal());
    }

    @Test
    public void updateWithAnotherUser() {
        Meal updated = getUpdatedMeal();
        assertThrows(NotFoundException.class, () -> service.update(updated, ADMIN_ID));
    }

    @Test
    public void create() {
        Meal created = service.create(getNewMeal(), USER_ID);
        Integer newId = created.getId();
        Meal newMeal = getNewMeal();
        newMeal.setId(newId);
        assertThat(created).isEqualTo(newMeal);
        assertThat(service.get(newId, USER_ID)).isEqualTo(newMeal);
    }

    @Test
    public void duplicateDateTimeCreate() {
        LocalDateTime duplicateDate = service.get(USER_MEAL1.getId(), USER_ID).getDateTime();
        assertThrows(DataAccessException.class,
                () -> service.create(new Meal(null, duplicateDate, "ужин", 1000), USER_ID));
    }
}
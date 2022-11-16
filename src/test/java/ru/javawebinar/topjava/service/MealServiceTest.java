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
    MealService service;

    @Test
    public void get() {
        Meal meal = service.get(TEST_MEAL1.getId(), USER_ID);
        assertThat(meal).isEqualTo(TEST_MEAL1);
    }

    @Test
    public void getWhithAnotherUser() {
        assertThrows(NotFoundException.class, () -> service.get(TEST_MEAL1.getId(), ADMIN_ID));
    }

    @Test
    public void delete() {
        service.delete(TEST_MEAL3.getId(), ADMIN_ID);
        assertThrows(NotFoundException.class, () -> service.delete(TEST_MEAL3.getId(), ADMIN_ID));
    }

    @Test
    public void deleteWhithAnotherUser() {
        assertThrows(NotFoundException.class, () -> service.delete(TEST_MEAL3.getId(), USER_ID));
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> betweenInclusive = service.getBetweenInclusive(START_TIME, END_TIME, USER_ID);
        assertThat(betweenInclusive).isEqualTo(Arrays.asList(TEST_MEAL4));
    }

    @Test
    public void getAll() {
        List<Meal> all = service.getAll(USER_ID);
        assertThat(all).isEqualTo(Arrays.asList(TEST_MEAL2, TEST_MEAL1));
    }

    @Test
    public void update() {
        Meal updated = getUpdatedMeal();
        service.update(updated, USER_ID);
        assertThat(service.get(TEST_MEAL1.getId(), USER_ID)).isEqualTo(getUpdatedMeal());
    }

    @Test
    public void updateWhithAnotherUser() {
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
    public void duplicateDateTimeCreate(){
        assertThrows(DataAccessException.class,
                ()-> service.create(new Meal(null, LocalDateTime.of(2022, 1, 22, 21, 0), "ужин", 1000), USER_ID));
    }
}
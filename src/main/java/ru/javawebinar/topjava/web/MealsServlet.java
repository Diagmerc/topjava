package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repo.InMemoryMealsMealsRepository;
import ru.javawebinar.topjava.repo.MealsRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.slf4j.LoggerFactory.getLogger;

public class MealsServlet extends HttpServlet {
    private static final Logger log = getLogger(MealsServlet.class);

    private MealsRepository repository;

    @Override
    public void init() {
        log.info("Start MealsServlet");
        repository = new InMemoryMealsMealsRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (!Arrays.asList("delete", "update", "create").contains(action)) {
            log.info("send List to view");
            request.setAttribute("meals", MealsUtil.mealsWhithExcessList(repository.getAll(), 2000));
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
        } else if (action.equals("delete")) {
            log.info("attribute delete");
            repository.delete(Integer.parseInt(request.getParameter("id")));
            response.sendRedirect("meals");
        } else {
            log.info(action.equals("update") ? "update" : "create");
            Meal meal = action.equals("update") ? repository.getById(Integer.parseInt(request.getParameter("id"))) :
                    new Meal(LocalDateTime.now(), "", 0);
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("/updateCreate.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        log.info("get attributes");
        String id = request.getParameter("id");
        Meal meal = new Meal(id.isEmpty() ? null : Integer.parseInt(id),
                LocalDateTime.parse((request.getParameter("date"))), request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));
        repository.save(meal);
        response.sendRedirect("meals");
    }
}

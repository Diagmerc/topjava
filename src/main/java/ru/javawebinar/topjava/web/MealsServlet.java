package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repo.RepoImpl;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealsServlet extends HttpServlet {
    private static final Logger log = getLogger(MealsServlet.class);

    private RepoImpl repository;

    @Override
    public void init() {
        log.info("Start MealsServlet");
        repository = new RepoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            log.info("action have attribute");
            switch (action) {
                case "delete":
                    log.info("attribute delete");
                    repository.deleteElement(Integer.parseInt(request.getParameter("id")));
                    response.sendRedirect("meals");
                    break;
                case "update": {
                    log.info("attribute update");
                    Meal meal = repository.getElement(Integer.parseInt(request.getParameter("id")));
                    request.setAttribute("meal", meal);
                    request.getRequestDispatcher("/update&create.jsp").forward(request, response);
                    break;
                }
                case "create": {
                    log.info("attribute create");
                    Meal meal = new Meal(LocalDateTime.now(), "", 0);
                    repository.saveElement(meal);
                    request.setAttribute("meal", meal);
                    request.getRequestDispatcher("/update&create.jsp").forward(request, response);
                    break;
                }
            }
        } else {
            log.info("send List to view");
            request.setAttribute("meals", MealsUtil.getFilteredWithExeeded(repository.getElements(), 2000));
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        log.info("get attributes");
        String id = request.getParameter("id");
        String date = request.getParameter("date");
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        if (id != null) {
            log.info("id not null");
            int parseId = Integer.parseInt(id);
            Meal meal = new Meal(LocalDateTime.parse(date), description, calories);
            repository.updateElement(parseId, meal);
            response.sendRedirect("meals");
        } else {
            log.info("id null");
            repository.saveElement(new Meal(LocalDateTime.parse(date), description, calories));
            response.sendRedirect("meals");
        }
    }
}

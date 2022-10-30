package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.repo.RepoImpl;
import ru.javawebinar.topjava.repo.Repository;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public class MealsServlet extends HttpServlet {
    private RepoImpl repository;

    @Override
    public void init() throws ServletException {
        repository = new RepoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            if (action.equals("delete")) {
                repository.deleteElement(Integer.parseInt(request.getParameter("id")));
                response.sendRedirect("meals");
            } else if (action.equals("update")) {
                Meal meal = repository.getElement(Integer.parseInt(request.getParameter("id")));
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("/update&create.jsp").forward(request, response);
            } else if (action.equals("create")) {
                Meal meal = new Meal(LocalDateTime.now(), "", 0);
                repository.saveElement(meal);
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("/update&create.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("meals", MealsUtil.getFilteredWithExeeded(repository.getElements(), 2000));
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        String date = request.getParameter("date");
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        if (id != null) {
            int parseId = Integer.parseInt(id);
            Meal meal = new Meal(parseId, LocalDateTime.now(), description, calories);
            repository.updateElement(parseId, meal);
            response.sendRedirect("meals");
        } else {
            repository.saveElement(new Meal(LocalDateTime.now(), description, calories));
            response.sendRedirect("meals");
        }
    }
}

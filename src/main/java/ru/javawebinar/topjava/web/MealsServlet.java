package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repo.Repository;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class MealsServlet extends HttpServlet {
    Repository repository = new Repository();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Meal> meals = repository.getMeals();
        request.setAttribute("meals", meals);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String date = request.getParameter("date");
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        repository.setMeal(new Meal(LocalDateTime.now(), description, calories));
        response.sendRedirect("meals");
    }
}

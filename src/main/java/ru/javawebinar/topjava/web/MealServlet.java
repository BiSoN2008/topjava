package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.repository.MealRepositoryInMemory;
import ru.javawebinar.topjava.repository.MealRepositoryInMemoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    MealRepositoryInMemory mealRepositoryInMemory = new MealRepositoryInMemoryImpl();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");

        String action = "listMeal";
        String param;
        param = request.getParameter("action");
        if (param != null){
            action = param;
        }

        if (action.equalsIgnoreCase("delete")){
            int id  = Integer.parseInt(request.getParameter("Id"));
            mealRepositoryInMemory.deleteById(id);
            response.sendRedirect("meals");

        }else
            if (action.equalsIgnoreCase("update")){
            int id = Integer.parseInt(request.getParameter("Id"));
        }
        else if (action.equalsIgnoreCase("listMeal"))
        {
            request.setAttribute("resultList", mealRepositoryInMemory.getList());
            request.getRequestDispatcher("/meals.jsp").forward(request,response);
        }

    }


}

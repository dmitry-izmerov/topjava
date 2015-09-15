package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.UserMealWithExceedDao;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Created by demi
 * on 11.09.15.
 */
public class MealCreateServlet extends HttpServlet
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.getRequestDispatcher("/mealCreateForm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try {
			String description = request.getParameter("description");
			int calories = Integer.parseInt(request.getParameter("calories"));
			LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"));
			boolean exceed = request.getParameterMap().containsKey("exceed") && request.getParameter("exceed").equals("on");
			UserMealWithExceed meal = new UserMealWithExceed(dateTime, description, calories, exceed);
			UserMealWithExceedDao.create(meal);
		} catch (Exception e) {
		}
		response.sendRedirect("/topjava/meals");
	}
}

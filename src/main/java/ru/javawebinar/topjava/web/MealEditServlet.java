package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.UserMealWithExceedDao;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by demi
 * on 11.09.15.
 */
public class MealEditServlet extends HttpServlet
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String pathInfo = request.getPathInfo();
		try {
			String[] parts = pathInfo.split("/");
			int id = Integer.parseInt(parts[1]);
			UserMealWithExceed meal = UserMealWithExceedDao.selectById(id);
			request.setAttribute("id", id);
			request.setAttribute("meal", meal);
		} catch (Exception e) {
			response.sendRedirect("/topjava/meals");
			return;
		}
		request.getRequestDispatcher("/mealEditForm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			String description = request.getParameter("description");
			int calories = Integer.parseInt(request.getParameter("calories"));
			LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"));
			boolean exceed = request.getParameterMap().containsKey("exceed") && request.getParameter("exceed").equals("on");
			UserMealWithExceed meal = new UserMealWithExceed(dateTime, description, calories, exceed);
			UserMealWithExceedDao.update(id, meal);
		} catch (Exception e) {
		}
		response.sendRedirect("/topjava/meals");
	}
}

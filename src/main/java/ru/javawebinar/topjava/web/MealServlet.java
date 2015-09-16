package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.UserMealWithExceedDao;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by demi
 * on 11.09.15.
 */
public class MealServlet extends HttpServlet
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		List<UserMealWithExceed> meals = UserMealWithExceedDao.selectAll();
		request.setAttribute("meals", meals);
		request.getRequestDispatcher("mealList.jsp").forward(request, response);
	}
}

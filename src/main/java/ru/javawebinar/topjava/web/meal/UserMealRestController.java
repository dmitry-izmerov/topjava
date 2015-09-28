package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class UserMealRestController
{

	protected final LoggerWrapper LOG = LoggerWrapper.get(getClass());

	@Autowired
	private UserMealService service;

	public List<UserMealWithExceed> getAll()
	{
		int userId = LoggedUser.id();
		LOG.info("getAll for User {}", userId);
		return UserMealsUtil.getFilteredWithExceeded(service.getAll(userId), LocalTime.MIN, LocalTime.MAX, LoggedUser.getCaloriesPerDay());
	}

	public UserMeal get(int id)
	{
		int userId = LoggedUser.id();
		LOG.info("get meal {} for User {}", id, userId);
		return service.get(userId, id);
	}

	public UserMeal save(UserMeal userMeal)
	{
		int userId = LoggedUser.id();
		LOG.info("save meal {} for User {}", userMeal.getId(), userId);
		return service.save(userId, userMeal);
	}

	public void delete(int id)
	{
		int userId = LoggedUser.id();
		LOG.info("delete meal {} for User {}", id, userId);
		service.delete(userId, id);
	}

	public List<UserMealWithExceed> getBetween(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime)
	{
		int userId = LoggedUser.id();
		LOG.info("getBetween for dates {} - {}, for time {} - {} for User {}", startDate, endDate, startTime, endTime, userId);
		return UserMealsUtil.getFilteredWithExceeded(service.getBetweenDates(userId, startDate, endDate), startTime, endTime, LoggedUser.getCaloriesPerDay());
	}
}

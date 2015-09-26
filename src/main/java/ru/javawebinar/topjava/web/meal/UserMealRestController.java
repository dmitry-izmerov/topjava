package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealServiceImpl;

import java.util.Collection;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class UserMealRestController
{

	protected final LoggerWrapper LOG = LoggerWrapper.get(getClass());

	@Autowired
	private UserMealServiceImpl service;

	public Collection<UserMeal> getAll(int userId)
	{
		LOG.info("getAll");
		return service.getAll(userId);
	}

	public UserMeal get(int userId, int id)
	{
		LOG.info("get " + id);
		return service.get(userId, id);
	}

	public UserMeal save(int userId, UserMeal userMeal)
	{
		LOG.info("save " + userMeal);
		return service.save(userId, userMeal);
	}

	public void delete(int userId, int id)
	{
		LOG.info("delete " + id);
		service.delete(userId, id);
	}
}

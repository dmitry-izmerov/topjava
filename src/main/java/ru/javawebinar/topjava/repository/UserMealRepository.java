package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.UserMeal;
import java.util.Map;

/**
 * GKislin
 * 06.03.2015.
 */
public interface UserMealRepository
{
	UserMeal save(int userId, UserMeal userMeal);

	boolean delete(int userId, int id);

	UserMeal get(int userId, int id);

	Map<Integer, UserMeal> getAll(int userId);

	LoggedUser getUser(UserMeal userMeal);
}

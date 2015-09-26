package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Map;

/**
 * GKislin
 * 15.06.2015.
 */
public interface UserMealService
{
	UserMeal get(int userId, int id) throws NotFoundException;

	Map<Integer, UserMeal> getAll(int userId) throws NotFoundException;;

	UserMeal save(int userId, UserMeal userMeal) throws NotFoundException;;

	void delete(int userId, int id) throws NotFoundException;;
}

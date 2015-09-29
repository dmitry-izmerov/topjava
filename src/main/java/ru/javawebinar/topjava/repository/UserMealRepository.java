package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
public interface UserMealRepository
{
	UserMeal save(int userId, UserMeal userMeal);

	boolean delete(int userId, int id);

	UserMeal get(int userId, int id);

	List<UserMeal> getAll(int userId);

	void deleteAll(int userId);

	List<UserMeal> getBetweenDateTimes(int userId, LocalDateTime start, LocalDateTime end);
}
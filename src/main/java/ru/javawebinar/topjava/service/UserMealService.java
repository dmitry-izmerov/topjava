package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 15.06.2015.
 */
public interface UserMealService
{
	UserMeal get(int userId, int id);

	List<UserMeal> getAll(int userId);

	UserMeal save(int userId, UserMeal userMeal);

	void delete(int userId, int id);

	void deleteAll(int userId);

	default List<UserMeal> getBetweenDates(int userId, LocalDate startDate, LocalDate endDate)
	{
		return getBetweenDateTimes(userId, LocalDateTime.of(startDate, LocalTime.MIN), LocalDateTime.of(endDate, LocalTime.MAX));
	}

	List<UserMeal> getBetweenDateTimes(int userId, LocalDateTime start, LocalDateTime end);

}

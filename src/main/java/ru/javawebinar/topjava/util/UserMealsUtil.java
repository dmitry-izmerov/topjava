package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil
{
	public static void main(String[] args)
	{
		List<UserMeal> mealList = Arrays.asList(
				new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
				new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
				new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
				new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
				new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
				new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
		);
		List<UserMealWithExceed> filteredMealsWithExceeded = getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
		filteredMealsWithExceeded.forEach(System.out::println);
	}

	/**
	 * Реализовать UserMealsUtil.getFilteredMealsWithExceeded:
		 -  должны возвращаться только записи между startTime и endTime
		 -  поле UserMealWithExceed.exceed должно показывать,
		 превышает ли сумма калорий за весь день параметра метода caloriesPerDay

		 Т.е UserMealWithExceed - это запись одной еды, но поле exceeded
		 будет одинаково для всех записей за этот день.

		 - Проверте результат выполнения ДЗ!
		 - Оцените Time complexity вашего алгоритма.
	 *
	 *
	 *
	 */
	private static List<UserMealWithExceed> getFilteredMealsWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay)
	{
		Map<LocalDate, Integer> sumCaloriesByDate = mealList
				.stream()
				.collect(Collectors.groupingBy(userMeal -> userMeal.getDateTime().toLocalDate(), Collectors.summingInt(UserMeal::getCalories)));

		// Note that if it is important that the elements for a given key appear in the order they appear in the source,
		// then we cannot use a concurrent reduction, as ordering is one of the casualties of concurrent insertion.
		// We would then be constrained to implement either a sequential reduction or a merge-based parallel reduction.

		return mealList.stream()
				.filter(userMeal -> TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime))
				.map(userMeal -> createMealWithExceed(userMeal, sumCaloriesByDate.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay))
				.collect(Collectors.toList());
	}

	private static UserMealWithExceed createMealWithExceed(UserMeal meal, boolean isExceed)
	{
		if (isExceed) {
			return new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), true);
		} else {
			return new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), false);
		}
	}

	// Time complexity - n
	private static List<UserMealWithExceed> getFilteredMealsWithExceededByCycle(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay)
	{
		Map<LocalDate, Integer> sumCaloriesByDate = new HashMap<>();
		for (UserMeal meal : mealList) {
			LocalDate mealDate = meal.getDateTime().toLocalDate();
			sumCaloriesByDate.put(mealDate, sumCaloriesByDate.getOrDefault(mealDate, 0) + meal.getCalories());
		}

		List<UserMealWithExceed> mealsWithExceed = new ArrayList<>();
		for (UserMeal meal : mealList) {
			if (TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime)) {
				addToMealsWithExceed(mealsWithExceed, meal, sumCaloriesByDate.get(meal.getDateTime().toLocalDate()) > caloriesPerDay);
			}
		}

		return mealsWithExceed;
	}

	private static void addToMealsWithExceed(List<UserMealWithExceed> mealsWithExceed, UserMeal meal, boolean isExceed)
	{
		if (isExceed) {
			mealsWithExceed.add(new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), true));
		} else {
			mealsWithExceed.add(new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), false));
		}
	}

}

package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

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
		System.out.println(getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));;
//        .toLocalDate();
//        .toLocalTime();
	}

	private static List<UserMealWithExceed> getFilteredMealsWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay)
	{
		return null;
	}


	public static List<UserMealWithExceed> getFilteredMealsWithExceeded01(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay)
	{
		List<UserMealWithExceed> withExceeds = new ArrayList<>();
		int sumCalories = 0;
		UserMeal prev = null;
		List<Integer> indexesPerDay = new ArrayList<>();
		int i = 0;
		for (UserMeal userMeal : mealList) {

			if (prev != null && !isPerOneDay(prev, userMeal)) {
				for (Integer index : indexesPerDay) {
					UserMeal prevMeal = mealList.get(index);
					if (TimeUtil.isBetween(prevMeal.getDateTime().toLocalTime(), startTime, endTime)) {
						withExceeds.add(new UserMealWithExceed(prevMeal.getDateTime(), prevMeal.getDescription(), prevMeal.getCalories(), false));
					}
				}
				indexesPerDay = new ArrayList<>();
				sumCalories = 0;
			}

			sumCalories += userMeal.getCalories();

			if (sumCalories > caloriesPerDay) {

				if (indexesPerDay.size() > 0) {
					for (Iterator<Integer> it = indexesPerDay.iterator(); it.hasNext();) {
						UserMeal prevMeal = mealList.get(it.next());
						if (TimeUtil.isBetween(prevMeal.getDateTime().toLocalTime(), startTime, endTime)) {
							withExceeds.add(new UserMealWithExceed(prevMeal.getDateTime(), prevMeal.getDescription(), prevMeal.getCalories(), true));
						}
						it.remove();
					}
				}

				if (TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime)) {
					withExceeds.add(new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), true));
				}
			} else {
				indexesPerDay.add(i);
			}


			prev = userMeal;
			++i;
		}
		return withExceeds;
	}

	private static boolean isPerOneDay(UserMeal prev, UserMeal userMeal)
	{
		LocalDateTime prevDateTime = prev.getDateTime();
		LocalDateTime userMealDateTime = userMeal.getDateTime();
		return prevDateTime.getYear() == userMealDateTime.getYear() &&
				prevDateTime.getMonthValue() == userMealDateTime.getMonthValue() &&
				prevDateTime.getDayOfMonth() == userMealDateTime.getDayOfMonth();
	}

}

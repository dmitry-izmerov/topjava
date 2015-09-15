package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by demi
 * on 15.09.15.
 */
public class UserMealWithExceedDao
{
	private static final List<UserMealWithExceed> mealList = new ArrayList<>(Arrays.asList(
			new UserMealWithExceed(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500, false),
			new UserMealWithExceed(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000, false),
			new UserMealWithExceed(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500, false),
			new UserMealWithExceed(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000, true),
			new UserMealWithExceed(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500, true),
			new UserMealWithExceed(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510, true)
	));

	public static final int OFFSET = 2;

	public static List<UserMealWithExceed> selectAll()
	{
		return mealList;
	}
	public static UserMealWithExceed selectById(int id)
	{
		return mealList.get(id);
	}

	public static void create(UserMealWithExceed meal)
	{
		mealList.add(meal);
	}

	public static void update(int id, UserMealWithExceed meal)
	{
		mealList.remove(id);
		mealList.add(id, meal);
	}

	public static void delete(int id)
	{
		mealList.remove(id);
	}
}

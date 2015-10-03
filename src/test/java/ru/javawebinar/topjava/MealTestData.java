package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData
{
	public static final int USER_MEAL_ID = 1;
	public static final int ADMIN_MEAL_ID = 7;

	public static final UserMeal USER_MEAL = new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
	public static final UserMeal ADMIN_MEAL = new UserMeal(LocalDateTime.of(2015, Month.JUNE, 1, 14, 0), "Админ ланч", 510);

	public static final List<UserMeal> USER_MEALS = new ArrayList<>();
	public static final List<UserMeal> ADMIN_MEALS = new ArrayList<>();

	public static final LocalDate START_DATE = LocalDate.of(2015, Month.MAY, 31);
	public static final LocalDate END_DATE = LocalDate.of(2015, Month.MAY, 31);
	public static final List<UserMeal> USER_MEALS_BETWEEN_DATES = new ArrayList<>();

	public static final LocalDateTime START_DATE_TIME = LocalDateTime.of(2015, Month.MAY, 31, 13, 0);
	public static final LocalDateTime END_DATE_TIME = LocalDateTime.of(2015, Month.MAY, 31, 21, 0);
	public static final List<UserMeal> USER_MEALS_BETWEEN_DATE_TIMES = new ArrayList<>();

	static {
		USER_MEALS.add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
		USER_MEALS.add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
		USER_MEALS.add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
		USER_MEALS.add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
		USER_MEALS.add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));

		ADMIN_MEALS.add(new UserMeal(LocalDateTime.of(2015, Month.JUNE, 1, 14, 0), "Админ ланч", 510));
		ADMIN_MEALS.add(new UserMeal(LocalDateTime.of(2015, Month.JUNE, 1, 21, 0), "Админ ужин ", 510));

		USER_MEALS_BETWEEN_DATES.add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
		USER_MEALS_BETWEEN_DATES.add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
		USER_MEALS_BETWEEN_DATES.add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));

		USER_MEALS_BETWEEN_DATE_TIMES.add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
		USER_MEALS_BETWEEN_DATE_TIMES.add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
	}

	public static final ModelMatcher<UserMeal, String> MATCHER = new ModelMatcher<>(UserMeal::toString);

}

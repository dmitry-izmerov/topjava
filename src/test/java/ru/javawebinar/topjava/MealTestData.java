package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData
{

	public static final ModelMatcher<UserMeal, String> MATCHER = new ModelMatcher<>(UserMeal::toString);

	public static int USER_MEAL_ID_COUNTER = 100002;
	public static int userMealId01 = 100002;
	public static UserMeal userMeal01 = new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
	public static UserMeal updatedUserMeal01 = new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак changed", 700);

	public static UserMeal userMeal02 = new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000);
	public static UserMeal userMeal03 = new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500);
	public static int userMealIdForSave = 100010;
	public static UserMeal userMealForSave = new UserMeal(LocalDateTime.of(2015, Month.OCTOBER, 12, 18, 0), "test supper", 1000);
	public static int userMealIdForDelete = 100008;
	public static UserMeal userMealForDelete = new UserMeal(LocalDateTime.of(2015, Month.JUNE, 1, 14, 0), "Админ ланч", 510);

	public static List<UserMeal> userMealsBetween = new ArrayList<>();

	static {
		userMeal01.setId(USER_MEAL_ID_COUNTER++);
		userMeal02.setId(USER_MEAL_ID_COUNTER++);
		userMeal03.setId(USER_MEAL_ID_COUNTER++);
		Collections.addAll(userMealsBetween, userMeal01, userMeal02, userMeal03);
	}

	public static int nonExistUserId = 0;
	public static int nonExistUserMealId = 0;

	public static LocalDateTime startDateTime = LocalDateTime.of(2015, Month.MAY, 30, 10, 0);
	public static LocalDateTime endDateTime = LocalDateTime.of(2015, Month.MAY, 30, 20, 0);
	public static LocalDate startDate = startDateTime.toLocalDate();
	public static LocalDate endDate = endDateTime.toLocalDate();


	public static List<UserMeal> userMealsAll = new ArrayList<>();

	public static UserMeal userMeal07 = new UserMeal(LocalDateTime.of(2015, Month.JUNE, 1, 21, 0), "Админ ужин", 1500);
	public static UserMeal updatedUserMeal07 = new UserMeal(LocalDateTime.of(2015, Month.JUNE, 1, 21, 0), "Админ ужин changed", 1700);

	static {
		userMealsAll.addAll(userMealsBetween);
		UserMeal userMeal = new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 500);
		userMeal.setId(USER_MEAL_ID_COUNTER++);
		userMealsAll.add(userMeal);
		userMeal = new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 1000);
		userMeal.setId(USER_MEAL_ID_COUNTER++);
		userMealsAll.add(userMeal);
		userMeal = new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510);
		userMeal.setId(USER_MEAL_ID_COUNTER++);
		userMealsAll.add(userMeal);
		userMealForDelete.setId(USER_MEAL_ID_COUNTER++);
	}
}

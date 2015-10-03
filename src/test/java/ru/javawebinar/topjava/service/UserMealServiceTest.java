package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

/**
 * Created by demi
 * on 03.10.15.
 */
@ContextConfiguration({"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMealServiceTest
{
	@Autowired
	UserMealService service;

	@Autowired
	DbPopulator dbPopulator;

	@Before
	public void setUp() throws Exception
	{
		dbPopulator.execute();
	}

	@Test
	public void testSave() throws Exception
	{
		UserMeal meal =  new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", USER_ID);
		UserMeal created = service.save(meal, USER_ID);
		meal.setId(created.getId());
		MealTestData.USER_MEALS.add(MealTestData.USER_MEAL);
		MealTestData.MATCHER.assertCollectionEquals(MealTestData.USER_MEALS, service.getAll(USER_ID));
	}

	@Test
	public void testDelete() throws Exception
	{
		service.delete(MealTestData.USER_MEAL_ID, UserTestData.USER_ID);
		MealTestData.USER_MEALS.remove(MealTestData.USER_MEAL);
		MealTestData.MATCHER.assertCollectionEquals(MealTestData.USER_MEALS, service.getAll(UserTestData.USER_ID));
	}

	@Test(expected = NotFoundException.class)
	public void testDeleteNotFound()
	{
		service.delete(100, 100);
	}

	@Test
	public void testGet() throws Exception
	{
		UserMeal meal = service.get(MealTestData.USER_MEAL_ID, UserTestData.USER_ID);
		MealTestData.MATCHER.assertEquals(MealTestData.USER_MEAL, meal);
	}

	@Test(expected = NotFoundException.class)
	public void testGetNotFound() throws Exception
	{
		service.get(100, 100);
	}

	@Test
	public void testGetBetweenDates() throws Exception
	{
		Collection<UserMeal> meals = service.getBetweenDates(MealTestData.START_DATE, MealTestData.END_DATE, UserTestData.USER_ID);
		MealTestData.MATCHER.assertCollectionEquals(MealTestData.USER_MEALS_BETWEEN_DATES, meals);
	}

	@Test
	public void testGetBetweenDateTimes() throws Exception
	{
		Collection<UserMeal> meals = service.getBetweenDateTimes(MealTestData.START_DATE_TIME, MealTestData.END_DATE_TIME, UserTestData.USER_ID);
		MealTestData.MATCHER.assertCollectionEquals(MealTestData.USER_MEALS_BETWEEN_DATE_TIMES, meals);
	}

	@Test
	public void testGetAll() throws Exception
	{
		MealTestData.MATCHER.assertCollectionEquals(MealTestData.USER_MEALS, service.getAll(UserTestData.USER_ID));
	}

	@Test
	public void testUpdate() throws Exception
	{
		UserMeal meal = MealTestData.ADMIN_MEAL;
		meal.setId(MealTestData.ADMIN_MEAL_ID);
		meal.setCalories(550);
		meal.setDescription("Admin lunch changed");
		service.update(meal, UserTestData.ADMIN_ID);
		MealTestData.MATCHER.assertEquals(meal, service.get(MealTestData.ADMIN_MEAL_ID, UserTestData.ADMIN_ID));
	}

	@Test(expected = NotFoundException.class)
	public void testUpdateNotFound() throws Exception
	{
		UserMeal meal = MealTestData.ADMIN_MEAL;
		meal.setId(MealTestData.ADMIN_MEAL_ID);
		service.update(meal, 100);
	}
}
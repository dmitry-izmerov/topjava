package ru.javawebinar.topjava.service;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collection;

import static ru.javawebinar.topjava.MealTestData.*;
/**
 * Created by demi
 * on 09.10.15.
 */
@ContextConfiguration({"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF8"))
public class UserMealServiceTest
{
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Autowired
	UserMealService userMealService;

	@Test
	public void testGet() throws Exception
	{
		UserMeal userMeal = userMealService.get(userMealId01, UserTestData.USER_ID);
		userMeal01.setId(userMealId01);
		MATCHER.assertEquals(userMeal01, userMeal);
	}

	@Test
	public void testGetNotFound()
	{
		expectedException.expect(NotFoundException.class);
		userMealService.get(nonExistUserMealId, nonExistUserId);
	}

	@Test
	public void testGetBetweenDates() throws Exception
	{
		Collection<UserMeal> betweenDates = userMealService.getBetweenDates(startDate, endDate, UserTestData.USER_ID);
		MATCHER.assertCollectionEquals(userMealsBetween, betweenDates);
	}

	@Test
	public void testGetBetweenDateTimes() throws Exception
	{
		Collection<UserMeal> betweenDateTimes = userMealService.getBetweenDateTimes(startDateTime, endDateTime, UserTestData.USER_ID);
		MATCHER.assertCollectionEquals(userMealsBetween, betweenDateTimes);
	}

	@Test
	public void testGetAll() throws Exception
	{
		Collection<UserMeal> all = userMealService.getAll(UserTestData.USER_ID);
		MATCHER.assertCollectionEquals(userMealsAll, all);
	}

	@Test
	public void testSave() throws Exception
	{
		UserMeal userMeal = userMealService.save(userMealForSave, UserTestData.ADMIN_ID);
		userMealForSave.setId(userMealIdForSave);
		MATCHER.assertEquals(userMealForSave, userMeal);
		MATCHER.assertEquals(userMealForSave, userMealService.get(userMealIdForSave, UserTestData.ADMIN_ID));
	}

	@Test
	public void testDelete() throws Exception
	{
		userMealService.delete(userMealIdForDelete, UserTestData.ADMIN_ID);
		expectedException.expect(NotFoundException.class);
		userMealService.get(userMealIdForDelete, UserTestData.ADMIN_ID);
	}

	@Test
	public void testUpdate() throws Exception
	{
		userMeal07.setDescription("Админ ужин changed");
		userMeal07.setCalories(1700);
		UserMeal updated = userMealService.update(userMeal07, UserTestData.USER_ID);
		updatedUserMeal07.setId(updated.getId());
		MATCHER.assertEquals(updatedUserMeal07, updated);
	}
}
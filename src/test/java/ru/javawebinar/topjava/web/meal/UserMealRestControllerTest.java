package ru.javawebinar.topjava.web.meal;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static ru.javawebinar.topjava.MealTestData.*;

/**
 * Created by demi
 * on 30.10.15.
 */
public class UserMealRestControllerTest extends AbstractMealControllerTest
{

	@Test
	public void testGetAll() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get(UserMealRestController.REST_URL).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MATCHER_WITH_EXCEED.contentListMatcher(USER_MEALS_WITH_EXCEED));
	}

	@Test
	public void testGetById() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get(UserMealRestController.REST_URL + MEAL1_ID).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MATCHER.contentMatcher(MEAL1));
	}

	@Test
	public void testCreate() throws Exception
	{
		UserMeal expected = new UserMeal(LocalDateTime.of(2015, Month.NOVEMBER, 1, 16, 0), "milk protein 99%", 800);
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(UserMealRestController.REST_URL).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(JsonUtil.writeValue(expected)))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isCreated());

		UserMeal returned = MATCHER.fromJsonAction(resultActions);
		expected.setId(returned.getId());

		MATCHER.assertEquals(expected, returned);
		List<UserMeal> userMeals = new ArrayList<>(USER_MEALS);
		userMeals.add(0, expected);

		MATCHER.assertCollectionEquals(userMeals, service.getAll(UserTestData.USER_ID));
	}

	@Test
	public void testDelete() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.delete(UserMealRestController.REST_URL + MEAL1_ID))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());

		List<UserMeal> userMeals = new ArrayList<>(USER_MEALS);
		userMeals.remove(userMeals.size() - 1);
		MATCHER.assertCollectionEquals(userMeals, service.getAll(UserTestData.USER_ID));
	}

	@Test
	public void testUpdate() throws Exception
	{
		UserMeal expected = MEAL1;
		expected.setDescription("milk protein 99%");
		expected.setCalories(800);

		mockMvc.perform(MockMvcRequestBuilders.post(UserMealRestController.REST_URL).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(JsonUtil.writeValue(expected)))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isCreated());

		MATCHER.assertCollectionEquals(USER_MEALS, service.getAll(UserTestData.USER_ID));
	}

	/*public void testGetBetween() throws Exception
	{
		ObjectMapper objectMapper = new ObjectMapper();
		LocalDateTime start = LocalDateTime.of(2015, Month.MAY, 30, 10, 0);
		LocalDateTime end = LocalDateTime.of(2015, Month.MAY, 30, 16, 0);
		Map<String, String> params = new HashMap<>();
		params.put("startDate", start.toLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
		params.put("endDate", end.toLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
		params.put("startTime", start.toLocalTime().format(DateTimeFormatter.ISO_LOCAL_TIME));
		params.put("endTime", end.toLocalTime().format(DateTimeFormatter.ISO_LOCAL_TIME));

		String content = JsonUtil.writeValue(objectMapper.writeValueAsString(params));
//		System.out.println(content);
		mockMvc.perform(MockMvcRequestBuilders.post(UserMealRestController.REST_URL + "filter").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(content))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MATCHER_WITH_EXCEED.contentListMatcher(Arrays.asList(MEAL_WITH_EXCEED_2, MEAL_WITH_EXCEED_1)));
	}*/
}
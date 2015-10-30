package ru.javawebinar.topjava.web.meal;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.web.AbstractControllerTest;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.*;
import static ru.javawebinar.topjava.MealTestData.MEAL1;
import static ru.javawebinar.topjava.MealTestData.MEAL1_ID;
import static ru.javawebinar.topjava.MealTestData.MEAL2;

/**
 * Created by demi
 * on 28.10.15.
 */
public class UserMealControllerTest extends AbstractControllerTest
{
	@Test
	public void testMealList() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("/meals"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("mealList"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/mealList.jsp"))
				.andExpect(MockMvcResultMatchers.model().attribute("mealList", Matchers.hasSize(6)));
	}

	@Test
	public void testDelete() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("/meals/delete?id=" + MEAL1_ID))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.redirectedUrl("meals"));
	}

	@Test
	public void testEditForUpdate() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("/meals/update?id=" + MEAL1_ID))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("mealEdit"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/mealEdit.jsp"))
				.andExpect(MockMvcResultMatchers.model().attribute("meal", is(
						allOf(
								hasProperty("id", is(MEAL1_ID)),
								hasProperty("dateTime", is(MealTestData.MEAL1.getDateTime())),
								hasProperty("description", is(MealTestData.MEAL1.getDescription())),
								hasProperty("calories", is(MealTestData.MEAL1.getCalories()))
						)
				)));
	}

	@Test
	public void testEditForCreate() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("/meals/create"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("mealEdit"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/mealEdit.jsp"))
				.andExpect(MockMvcResultMatchers.model().attribute("meal", is(
						allOf(
								hasProperty("id", is(nullValue())),
								hasProperty("description", isEmptyString()),
								hasProperty("calories", is(1000))
						)
				)));
	}

	@Test
	public void testUpdateOrCreate() throws Exception
	{
		mockMvc.perform(
				MockMvcRequestBuilders.post("/meals")
				.param("id", "")
				.param("dateTime", LocalDateTime.of(2015, Month.NOVEMBER, 1, 14, 0).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
				.param("description", "test meal")
				.param("calories", "1000")
		)
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.redirectedUrl("meals"));
	}

	@Test
	public void testGetBetween() throws Exception
	{
		LocalDateTime start = LocalDateTime.of(2015, Month.MAY, 30, 10, 0);
		LocalDateTime end = LocalDateTime.of(2015, Month.MAY, 30, 16, 0);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/meals/filter")
						.param("startDate", start.toLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE))
						.param("endDate", end.toLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE))
						.param("startTime", start.toLocalTime().format(DateTimeFormatter.ISO_LOCAL_TIME))
						.param("endTime", end.toLocalTime().format(DateTimeFormatter.ISO_LOCAL_TIME))
		)
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("mealList"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/mealList.jsp"))
				.andExpect(MockMvcResultMatchers.model().attribute("mealList", hasItems(
						is(allOf(
							hasProperty("id", is(MEAL1_ID)),
							hasProperty("dateTime", is(MEAL1.getDateTime())),
							hasProperty("description", is(MEAL1.getDescription())),
							hasProperty("calories", is(MEAL1.getCalories()))
						)),
						is(allOf(
								hasProperty("id", is(MEAL1_ID + 1)),
								hasProperty("dateTime", is(MEAL2.getDateTime())),
								hasProperty("description", is(MEAL2.getDescription())),
								hasProperty("calories", is(MEAL2.getCalories()))
						))
				)))
				.andExpect(MockMvcResultMatchers.model().attribute("mealList", hasSize(2)));

	}
}
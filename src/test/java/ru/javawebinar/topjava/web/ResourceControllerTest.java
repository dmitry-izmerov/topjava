package ru.javawebinar.topjava.web;

import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Created by demi
 * on 28.10.15.
 */
public class ResourceControllerTest extends AbstractControllerTest
{
	@Test
	public void testStyleCss() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("/resources/css/style.css"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("text/css"));
	}
}

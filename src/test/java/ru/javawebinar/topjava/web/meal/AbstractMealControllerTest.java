package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.web.AbstractControllerTest;

/**
 * Created by demi
 * on 28.10.15.
 */
public class AbstractMealControllerTest extends AbstractControllerTest
{
	@Autowired
	protected UserMealService service;

}

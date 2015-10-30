package ru.javawebinar.topjava.web.user;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.web.AbstractControllerTest;

/**
 * Created by demi
 * on 28.10.15.
 */
public class AbstractUserControllerTest extends AbstractControllerTest
{
	@Autowired
	protected UserService service;

	@Before
	public void setUp() {
		service.evictCache();
	}
}

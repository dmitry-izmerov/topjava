package ru.javawebinar.topjava.service;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.util.exception.ExceptionUtil;

import static org.junit.Assert.*;

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
	ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testGet() throws Exception
	{

	}

	@Test
	public void testDelete() throws Exception
	{

	}

	@Test
	public void testGetBetweenDates() throws Exception
	{

	}

	@Test
	public void testGetBetweenDateTimes() throws Exception
	{

	}

	@Test
	public void testGetAll() throws Exception
	{

	}

	@Test
	public void testUpdate() throws Exception
	{

	}

	@Test
	public void testSave() throws Exception
	{

	}
}
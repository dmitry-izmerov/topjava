package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.exception.ExceptionUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Service
public class UserMealServiceImpl implements UserMealService
{

	@Autowired
	private UserMealRepository repository;

	@Override
	public UserMeal get(int userId, int id)
	{
		return ExceptionUtil.check(repository.get(userId, id), id);
	}

	@Override
	public List<UserMeal> getAll(int userId)
	{
		return repository.getAll(userId);
	}

	@Override
	public UserMeal save(int userId, UserMeal userMeal)
	{
		return repository.save(userId, userMeal);
	}

	@Override
	public void delete(int userId, int id)
	{
		repository.delete(userId, id);
	}

	@Override
	public void deleteAll(int userId)
	{
		repository.deleteAll(userId);
	}

	@Override
	public List<UserMeal> getBetweenDateTimes(int userId, LocalDateTime start, LocalDateTime end)
	{
		return repository.getBetweenDateTimes(userId, start, end);
	}
}

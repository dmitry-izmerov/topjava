package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import java.util.Map;

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
		return repository.get(userId, id);
	}

	@Override
	public Map<Integer, UserMeal> getAll(int userId)
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
}

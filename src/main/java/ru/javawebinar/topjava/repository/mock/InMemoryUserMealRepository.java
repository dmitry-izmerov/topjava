package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryUserMealRepository implements UserMealRepository
{
	private Map<Integer, Map<Integer, UserMeal>> repository = new ConcurrentHashMap<>();
	private AtomicInteger counter = new AtomicInteger(0);

	{
		save(LoggedUser.id(), new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
		save(LoggedUser.id(), new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
		save(LoggedUser.id(), new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
		save(LoggedUser.id(), new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
		save(LoggedUser.id(), new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
		save(LoggedUser.id(), new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
	}

	@Override
	public UserMeal save(int userId, UserMeal userMeal)
	{
		if (userMeal.isNew()) {
			userMeal.setId(counter.incrementAndGet());
		}
		Map<Integer, UserMeal> map = null;
		if (repository.containsKey(userId)) {
			map = repository.get(userId);
		} else {
			map = repository.get(userId);
		}
		map.put(userMeal.getId(), userMeal);
		repository.put(userId, map);
		return userMeal;
	}

	@Override
	public boolean delete(int userId, int id)
	{
		if (repository.containsKey(userId) && repository.get(userId).containsKey(id)) {
			repository.get(userId).remove(id);
			return true;
		}
		return false;
	}

	@Override
	public UserMeal get(int userId, int id)
	{
		if (repository.containsKey(userId) && repository.get(userId).containsKey(id)) {
			return repository.get(userId).get(id);
		}
		return null;
	}

	@Override
	public Map<Integer, UserMeal> getAll(int userId)
	{
		if (repository.containsKey(userId)) {
			return repository.get(userId);
		}
		return Collections.emptyMap();
	}

	@Override
	public LoggedUser getUser(UserMeal userMeal)
	{
		return userMeal.getUser();
	}
}


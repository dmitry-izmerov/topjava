package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.UserMealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryUserMealRepository implements UserMealRepository
{
	private Map<Integer, List<UserMeal>> repository = new ConcurrentHashMap<>();
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
		if (repository.containsKey(userId)) {
			List<UserMeal> userMeals = repository.get(userId);
			userMeals.add(userMeal);
		} else {
			List<UserMeal> userMeals = new ArrayList<>();
			userMeals.add(userMeal);
			repository.put(userId, userMeals);
		}
		return userMeal;
	}

	@Override
	public boolean delete(int userId, int id)
	{
		if (repository.containsKey(userId)) {
			List<UserMeal> meals = repository.get(userId);
			for (Iterator<UserMeal> it = meals.iterator(); it.hasNext();) {
				UserMeal meal = it.next();
				if (meal.getId() == id) {
					it.remove();
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public UserMeal get(int userId, int id)
	{
		if (repository.containsKey(userId) ) {
			for (UserMeal meal : repository.get(userId)) {
				if (meal.getId() == id) {
					return meal;
				}
			}
		}
		return null;
	}

	@Override
	public List<UserMeal> getAll(int userId)
	{
		if (repository.containsKey(userId)) {
			return repository.get(userId);
		}
		return Collections.emptyList();
	}

	@Override
	public void deleteAll(int userId)
	{
		if (repository.containsKey(userId)) {
			repository.remove(userId);
		}
	}

	@Override
	public List<UserMeal> getBetweenDateTimes(int userId, LocalDateTime start, LocalDateTime end)
	{
		return getAll(userId).stream().filter(meal -> TimeUtil.isBetween(meal.getDateTime(), start, end)).collect(Collectors.toList());
	}
}


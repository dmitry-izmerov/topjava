package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * User: gkisline
 * Date: 26.08.2014
 */

@Repository
public class JpaUserMealRepositoryImpl implements UserMealRepository
{

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public UserMeal save(UserMeal userMeal, int userId)
	{
		User ref = em.getReference(User.class, userId);
		userMeal.setUser(ref);
		if (userMeal.isNew()) {
			em.persist(userMeal);
			return userMeal;
		} else {
			return em.merge(userMeal);
		}
	}

	@Override
	@Transactional
	public boolean delete(int id, int userId)
	{
		return em.createNamedQuery(UserMeal.DELETE).setParameter("id", id).setParameter("user_id", userId).executeUpdate() != 0;
	}

	@Override
	public UserMeal get(int id, int userId)
	{
		Map<String, Object> map = new HashMap<>();
		User ref = em.getReference(User.class, userId);
		map.put("user", ref);
		return em.find(UserMeal.class, id, map);
	}

	@Override
	public List<UserMeal> getAll(int userId)
	{
		return em.createNamedQuery(UserMeal.GET_ALL, UserMeal.class).setParameter("user_id", userId).getResultList();
	}

	@Override
	public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId)
	{
		return em.createNamedQuery(UserMeal.GET_BETWEEN, UserMeal.class)
				.setParameter("user_id", userId)
				.setParameter("start", startDate)
				.setParameter("end", endDate)
				.getResultList();
	}
}
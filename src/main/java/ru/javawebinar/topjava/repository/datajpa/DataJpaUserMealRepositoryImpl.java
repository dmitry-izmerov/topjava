package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

/**
 * GKislin
 * 27.03.2015.
 */
@Repository
public class DataJpaUserMealRepositoryImpl implements UserMealRepository {

	@Autowired
	private ProxyUserMealRepository proxy;

	@PersistenceContext
	private EntityManager em;

    @Override
	@Transactional
    public UserMeal save(UserMeal userMeal, int userId) {
		User ref = em.getReference(User.class, userId);
		userMeal.setUser(ref);

		if (userMeal.isNew()) {
			em.persist(userMeal);
			return userMeal;
		} else {
			if (get(userMeal.getId(), userId) == null) return null;
			return em.merge(userMeal);
		}
    }

    @Override
    public boolean delete(int id, int userId) {
        return proxy.delete(id, userId) != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        return proxy.get(id, userId);
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return proxy.findAllByUserIdOrderByDateTimeDesc(userId);
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return proxy.getBetween(startDate, endDate, userId);
    }

	@Override
	public UserMeal getWithUser(int id, int userId)
	{
		return proxy.getWithUser(id, userId);
	}
}

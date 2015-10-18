package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by demi
 * on 17.10.15.
 */
public interface ProxyUserMealRepository extends JpaRepository<UserMeal, Integer>
{
	@Modifying
	@Transactional
	@Query("DELETE FROM UserMeal um WHERE um.id = :id AND um.user.id = :user_id")
	int delete(@Param("id") int id, @Param("user_id") int userId);

	@Query("SELECT um FROM UserMeal um WHERE um.id = :id AND um.user.id = :user_id")
	UserMeal get(@Param("id") int id, @Param("user_id") int userId);

	List<UserMeal> findAllByUserIdOrderByDateTimeDesc(Integer userId);

	List<UserMeal> getBetween(@Param("startDate") LocalDateTime startDate,@Param("endDate") LocalDateTime endDate,@Param("userId") int userId);

	@Query("SELECT um FROM UserMeal um JOIN FETCH um.user WHERE um.id = :id AND um.user.id = :user_id")
	UserMeal getWithUser(@Param("id") int id,@Param("user_id") int userId);
}

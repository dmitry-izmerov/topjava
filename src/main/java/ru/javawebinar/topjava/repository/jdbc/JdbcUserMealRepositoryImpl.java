package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.TimeUtil;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.List;

/**
 * User: gkislin
 * Date: 26.08.2014
 */

@Repository
public class JdbcUserMealRepositoryImpl implements UserMealRepository
{
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	RowMapper<UserMeal> rowMapper = (rs, rowNum) -> {
		UserMeal userMeal = new UserMeal();
		userMeal.setId(rs.getInt("id"));
		String dateString = rs.getString("datetime");
		userMeal.setDateTime(TimeUtil.parsePSQLTimestamp(dateString));
		userMeal.setDescription(rs.getString("description"));
		userMeal.setCalories(rs.getInt("calories"));
		return userMeal;
	};

	@Override
	public UserMeal save(UserMeal userMeal, int userId)
	{
		if (userMeal.isNew()) {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(connection -> {
				PreparedStatement ps = connection.prepareStatement("INSERT INTO meals (datetime, description, calories, user_id) VALUES (?, ?, ?, ?)");
				ps.setString(1, userMeal.getDateTime().toString());
				ps.setString(2, userMeal.getDescription());
				ps.setInt(3, userMeal.getCalories());
				ps.setInt(4, userId);
				return ps;
			}, keyHolder);
			userMeal.setId((Integer) keyHolder.getKey());
		} else {
			jdbcTemplate.update(
					"UPDATE meals SET datetime = ?, description = ?, calories = ? WHERE id = ? AND user_id = ?;",
					userMeal.getDateTime().toString(),
					userMeal.getDescription(),
					userMeal.getCalories(),
					userMeal.getId(),
					userId
			);
		}
		return userMeal;
	}

	@Override
	public boolean delete(int id, int userId)
	{
		return jdbcTemplate.update("DELETE FROM meals WHERE id = ?", id) != 0;
	}

	@Override
	public UserMeal get(int id, int userId)
	{
		return jdbcTemplate.queryForObject("SELECT * FROM meals WHERE id = ? AND user_id = ?;", rowMapper, id, userId);
	}

	@Override
	public List<UserMeal> getAll(int userId)
	{
		return jdbcTemplate.query("SELECT * FROM meals WHERE user_id = ? ORDER BY id DESC;", rowMapper, userId);
	}

	@Override
	public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId)
	{
		return jdbcTemplate.query(
				"SELECT * FROM meals WHERE user_id = ? AND datetime BETWEEN ?::timestamp AND ?::timestamp ORDER BY id DESC;",
				rowMapper,
				userId,
				TimeUtil.toPSQLFormat(startDate),
				TimeUtil.toPSQLFormat(endDate)
		);
	}
}
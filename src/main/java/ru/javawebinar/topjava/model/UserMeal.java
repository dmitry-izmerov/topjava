package ru.javawebinar.topjava.model;

import ru.javawebinar.topjava.LoggedUser;

import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */
public class UserMeal extends BaseEntity
{
	protected final LocalDateTime dateTime;

	protected final String description;

	protected final int calories;

	private LoggedUser user;

	public UserMeal(LocalDateTime dateTime, String description, int calories)
	{
		this(null, dateTime, description, calories);
	}

	public UserMeal(Integer id, LocalDateTime dateTime, String description, int calories)
	{
		this.id = id;
		this.dateTime = dateTime;
		this.description = description;
		this.calories = calories;
	}

	public LocalDateTime getDateTime()
	{
		return dateTime;
	}

	public String getDescription()
	{
		return description;
	}

	public int getCalories()
	{
		return calories;
	}

	@Override
	public String toString()
	{
		return "UserMeal{" +
				"id=" + id +
				", dateTime=" + dateTime +
				", description='" + description + '\'' +
				", calories=" + calories +
				'}';
	}

	public LoggedUser getUser()
	{
		return user;
	}

	public void setUser(LoggedUser user)
	{
		this.user = user;
	}
}
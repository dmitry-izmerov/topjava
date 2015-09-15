package ru.javawebinar.topjava.model;

import ru.javawebinar.topjava.dao.UserMealWithExceedDao;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * GKislin
 * 11.01.2015.
 */
public class UserMealWithExceed
{
	public static final String IS_EXCEED_CSS_CLASS = "is-exceed";

	public static final String IS_NOT_EXCEED_CSS_CLASS = "is-not-exceed";

	protected final LocalDateTime dateTime;

	protected final String description;

	protected final int calories;

	protected final boolean exceed;

	public UserMealWithExceed(LocalDateTime dateTime, String description, int calories, boolean exceed)
	{
		this.dateTime = dateTime;
		this.description = description;
		this.calories = calories;
		this.exceed = exceed;
	}

	public UserMealWithExceed(UserMealWithExceed meal)
	{
		this.dateTime = meal.dateTime;
		this.description = meal.description;
		this.calories = meal.calories;
		this.exceed = meal.exceed;
	}

	public String getDescription()
	{
		return description;
	}

	public int getCalories()
	{
		return calories;
	}

	public boolean isExceed()
	{
		return exceed;
	}

	public LocalDateTime getDateTime()
	{
		return dateTime;
	}

	public long getTimeStamp()
	{
		return dateTime.toEpochSecond(ZoneOffset.ofHours(UserMealWithExceedDao.OFFSET));
	}

	@Override
	public String toString()
	{
		return "UserMealWithExceed{" +
				"dateTime=" + dateTime +
				", description='" + description + '\'' +
				", calories=" + calories +
				", exceed=" + exceed +
				'}';
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserMealWithExceed that = (UserMealWithExceed) o;

		if (calories != that.calories) return false;
		if (exceed != that.exceed) return false;
		if (dateTime != null ? !dateTime.equals(that.dateTime) : that.dateTime != null) return false;
		return !(description != null ? !description.equals(that.description) : that.description != null);
	}

	@Override
	public int hashCode()
	{
		int result = dateTime != null ? dateTime.hashCode() : 0;
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + calories;
		result = 31 * result + (exceed ? 1 : 0);
		return result;
	}

	public String getCssClass()
	{
		if (exceed) {
			return IS_EXCEED_CSS_CLASS;
		} else {
			return IS_NOT_EXCEED_CSS_CLASS;
		}
	}
}

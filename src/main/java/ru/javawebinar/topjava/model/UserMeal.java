package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */
public class UserMeal extends BaseEntity
{

	protected LocalDateTime dateTime;

	protected String description;

	protected int calories;

	public UserMeal()
	{
	}

	public UserMeal(LocalDateTime dateTime, String description, int calories)
	{
		this(null, dateTime, description, calories);
	}

	public UserMeal(Integer id, LocalDateTime dateTime, String description, int calories)
	{
		super(id);
		this.dateTime = dateTime;
		this.description = description;
		this.calories = calories;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public LocalDateTime getDateTime()
	{
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime)
	{
		this.dateTime = dateTime;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public int getCalories()
	{
		return calories;
	}

	public void setCalories(int calories)
	{
		this.calories = calories;
	}

	public Integer getId()
	{
		return id;
	}

	public boolean isNew()
	{
		return id == null;
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

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		UserMeal userMeal = (UserMeal) o;

		if (calories != userMeal.calories) return false;
		if (dateTime != null ? !dateTime.equals(userMeal.dateTime) : userMeal.dateTime != null) return false;
		return !(description != null ? !description.equals(userMeal.description) : userMeal.description != null);

	}

	@Override
	public int hashCode()
	{
		int result = super.hashCode();
		result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + calories;
		return result;
	}
}
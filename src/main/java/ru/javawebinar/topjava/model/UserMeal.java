package ru.javawebinar.topjava.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */
@Entity
@Table(name = "meals")
@Access(value = AccessType.FIELD)
public class UserMeal extends BaseEntity
{

	@Column(name = "date_time", nullable = false)
	@NotEmpty
	protected LocalDateTime dateTime;

	@Column(name = "description", nullable = false)
	@NotEmpty
	protected String description;

	@Column(name = "calories", nullable = false)
	@NotEmpty
	protected int calories;

	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

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

	public String getDescription()
	{
		return description;
	}

	public int getCalories()
	{
		return calories;
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

	public void setDateTime(LocalDateTime dateTime)
	{
		this.dateTime = dateTime;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public void setCalories(int calories)
	{
		this.calories = calories;
	}
}
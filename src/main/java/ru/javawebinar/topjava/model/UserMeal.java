package ru.javawebinar.topjava.model;

import org.hibernate.validator.constraints.NotEmpty;
import ru.javawebinar.topjava.util.LocalDateTimeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */
@NamedQueries({
		@NamedQuery(name = UserMeal.DELETE, query = "DELETE FROM UserMeal um WHERE um.id=:id AND um.user.id = :user_id"),
		@NamedQuery(name = UserMeal.GET_ALL, query = "SELECT um FROM UserMeal um WHERE um.user.id = :user_id"),
		@NamedQuery(name = UserMeal.GET_BETWEEN, query = "SELECT um FROM UserMeal um WHERE um.user.id = :user_id AND um.dateTime >= :start AND um.dateTime <= :end ORDER BY um.dateTime"),
})
@Entity
@Table(name = "meals")
@Access(value = AccessType.FIELD)
public class UserMeal extends BaseEntity
{
	public static final String DELETE = "UserMeal.delete";
	public static final String GET_ALL = "UserMeal.getAll";
	public static final String GET_BETWEEN = "UserMeal.getBetween";

	@Column(name = "date_time", nullable = false)
	@NotEmpty
	@Convert(converter = LocalDateTimeConverter.class)
	protected LocalDateTime dateTime;

	@Column(name = "description", nullable = false)
	@NotEmpty
	protected String description;

	@Column(name = "calories", nullable = false)
	@NotEmpty
	protected int calories;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
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

	public void setUser(User user)
	{
		this.user = user;
	}

	public User getUser()
	{
		return user;
	}
}
package ru.javawebinar.topjava.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by demi
 * on 08.10.15.
 */
@Converter(autoApply = false)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp>
{
	@Override
	public java.sql.Timestamp convertToDatabaseColumn(LocalDateTime entityValue)
	{
		return Timestamp.valueOf(entityValue);
	}

	@Override
	public LocalDateTime convertToEntityAttribute(java.sql.Timestamp databaseValue)
	{
		return databaseValue.toLocalDateTime();
	}
}
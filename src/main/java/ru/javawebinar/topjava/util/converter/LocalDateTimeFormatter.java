package ru.javawebinar.topjava.util.converter;

import org.springframework.format.Formatter;
import ru.javawebinar.topjava.util.TimeUtil;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Created by demi
 * on 10.11.15.
 */
public class LocalDateTimeFormatter implements Formatter<LocalDateTime>
{

	@Override
	public LocalDateTime parse(String s, Locale locale) throws ParseException
	{
		return TimeUtil.parseLocalDateTime(s, DateTimeFormatter.ISO_DATE_TIME);
	}

	@Override
	public String print(LocalDateTime localDateTime, Locale locale)
	{
		return localDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
	}
}
